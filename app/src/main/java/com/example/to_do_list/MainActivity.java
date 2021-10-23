package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout todoList;
    Button addActivity;
    EditText textInfo;

    ArrayList<Activity> activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityList = new ArrayList<>();
        todoList = (LinearLayout) findViewById(R.id.todoList);
        addActivity = (Button) findViewById(R.id.addActivity);
        addActivity.setOnClickListener(this);
        textInfo = (EditText) findViewById(R.id.activityInfo);
        addActivity("Go To Hell",false);
        addActivity("Go To Hell",true);
        addActivity("Go To Hell",false);
        addActivity("Go To Hell",true);
        addActivity("Go To Hell",false);
    }

    public void addActivity(String info,boolean status){
        final View todoActivity = getLayoutInflater().inflate(R.layout.todo_activity,null,false);
        TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) todoActivity.findViewById(R.id.activity_status);
        Activity new_activity = new Activity(info,status);
        activityList.add(new_activity);
        activity.setText(new_activity.getActivityInfo());
        checkBox.setChecked(new_activity.getStaus());
        textInfo.setText("");
        todoList.addView(todoActivity);
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addActivity:
                addActivity(textInfo.getText().toString(),false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}