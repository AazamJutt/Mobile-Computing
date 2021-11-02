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
import android.widget.EditText;
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
    public static ArrayList<Activity> activityList;
    public static final String FIELANAME = "todolist.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityList = new ArrayList<>();
        todoList = (LinearLayout) findViewById(R.id.todoList);
        addActivity = (Button) findViewById(R.id.addActivity);
        shareReport = findViewById(R.id.btnShare);
        shareReport.setOnClickListener(this);
        addActivity.setOnClickListener(this);
        readData();
    }
    public void goToAddActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        startActivity(intent);
    }

    private String createReport(){
        StringBuilder str = new StringBuilder();
        for (Activity activity: activityList
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

    public void addActivity(String info,String dateString,boolean status){
        if(info.equals(""))
            return;
        final View todoActivity = getLayoutInflater().inflate(R.layout.todo_activity,null,false);
        TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) todoActivity.findViewById(R.id.activity_status);
        TextView date = (TextView) todoActivity.findViewById(R.id.date);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) todoActivity.getLayoutParams();
        Activity new_activity = new Activity(info,dateString,status);
        activityList.add(new_activity);
        int n = 20;
        setMargins(todoActivity,n,n,n,n);
        activity.setText(new_activity.getActivityInfo());
        if(status){
            activity.setPaintFlags(activity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        checkBox.setChecked(new_activity.getStaus());
        date.setText(dateString);
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
                        if(activityList.get(i).getActivityInfo().equals(activity.getText()))
                            activityList.get(i).setStaus(true);
                    }
                    activity.setPaintFlags(activity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    writeData();
                }
                else{
                    TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
                    for(int i=0;i<activityList.size();i++){
                        if(activityList.get(i).getActivityInfo().equals(activity.getText()))
                            activityList.get(i).setStaus(false);
                    }
                    activity.setPaintFlags(0);
                    writeData();
                }
            }
        });
    }

    private void removeView(View view){
        TextView activity = (TextView) view.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.activity_status);

        for(int i=0;i<activityList.size();i++){
            if(activityList.get(i).getActivityInfo().equals(activity.getText()))
                activityList.remove(i);
        }
        todoList.removeView(view);
        writeData();
    }



    public void readData(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(MainActivity.FIELANAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while((text = br.readLine())!=null){
                String[] fields = text.split(",");
                addActivity(fields[0],fields[1],Boolean.parseBoolean(fields[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeData(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(MainActivity.FIELANAME,MODE_PRIVATE);
            for (Activity activity:activityList) {
                String data = activity.getActivityInfo()+","+activity.getDate()+','+activity.getStaus()+'\n';
                fos.write(data.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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