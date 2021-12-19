package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout todoList;
    Button addActivity;
    Button shareReport;
    public static ArrayList<ActivityModel> activityList;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        activityList = dbHelper.getAllActivities();
        todoList = (LinearLayout) findViewById(R.id.todoList);
        addActivity = (Button) findViewById(R.id.addActivity);
        shareReport = findViewById(R.id.btnShare);
        shareReport.setOnClickListener(this);
        addActivity.setOnClickListener(this);
        initList();
    }
    public void goToAddActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        startActivity(intent);
    }

    private String createReport(){
        StringBuilder str = new StringBuilder();
        for (ActivityModel activity: activityList
             ) {
            str.append("Info: ").append(activity.getActivityInfo());
            str.append(" Due-Date: ").append(activity.getDate());
            str.append(" Status: ").append((activity.getStaus())?"Done":"Pending");
            str.append("\n");
        }
        return str.toString();
    }
    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    public void addActivity(ActivityModel new_activity){
        if(new_activity.getActivityInfo().equals(""))
            return;
        final View todoActivity = getLayoutInflater().inflate(R.layout.todo_activity,null,false);
        TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) todoActivity.findViewById(R.id.activity_status);
        TextView date = (TextView) todoActivity.findViewById(R.id.date);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) todoActivity.getLayoutParams();
        int n = 20;
        setMargins(todoActivity,n,n,n,n);
        activity.setText(new_activity.getActivityInfo());
        if(new_activity.getStaus()){
            activity.setPaintFlags(activity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        checkBox.setChecked(new_activity.getStaus());
        date.setText(new_activity.getDate());
        //textInfo.setText("");
        todoList.addView(todoActivity);
        ImageView imageClose = (ImageView)todoActivity.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(v -> removeView(todoActivity));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
                    for(int i=0;i<activityList.size();i++){
                        if(activityList.get(i).getActivityInfo().equals(activity.getText())) {
                            activityList.get(i).setStaus(true);
                            dbHelper.updateActivity(activityList.get(i));
                        }
                    }
                    activity.setPaintFlags(activity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else{
                    TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
                    for(int i=0;i<activityList.size();i++){
                        if(activityList.get(i).getActivityInfo().equals(activity.getText())) {
                            activityList.get(i).setStaus(false);
                            dbHelper.updateActivity(activityList.get(i));
                        }
                    }
                    activity.setPaintFlags(0);
                }
            }
        });
    }

    private void removeView(View view){
        TextView activity = (TextView) view.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.activity_status);

        for(int i=0;i<activityList.size();i++){
            if(activityList.get(i).getActivityInfo().equals(activity.getText())) {
                dbHelper.removeActivity(activityList.get(i));
                activityList.remove(i);
            }
        }
        todoList.removeView(view);

    }



    public void initList(){
        for (ActivityModel activity:this.activityList
             ) {
            addActivity(activity);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addActivity:
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShare:
                Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, createReport() );
                startActivity(Intent.createChooser(intent2, "Share via"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}