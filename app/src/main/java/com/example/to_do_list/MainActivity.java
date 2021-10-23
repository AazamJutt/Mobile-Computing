package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.icu.number.ScientificNotation;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout todoList;
    Button addActivity;
    EditText textInfo;
    ArrayList<Activity> activityList;
    private static final String FIELANAME = "todolist.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityList = new ArrayList<>();
        todoList = (LinearLayout) findViewById(R.id.todoList);
        addActivity = (Button) findViewById(R.id.addActivity);
        addActivity.setOnClickListener(this);
        textInfo = (EditText) findViewById(R.id.activityInfo);
        readData();
    }

    public void addActivity(String info,boolean status){
        if(info.equals(""))
            return;
        final View todoActivity = getLayoutInflater().inflate(R.layout.todo_activity,null,false);
        TextView activity = (TextView) todoActivity.findViewById(R.id.activity_info);
        CheckBox checkBox = (CheckBox) todoActivity.findViewById(R.id.activity_status);
        Activity new_activity = new Activity(info,status);
        activityList.add(new_activity);
        activity.setText(new_activity.getActivityInfo());
        if(status){
            activity.setPaintFlags(activity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        checkBox.setChecked(new_activity.getStaus());
        textInfo.setText("");
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
                addActivity(fields[0],Boolean.parseBoolean(fields[1]));
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
            for(int i=0;i<activityList.size();i++){
                String data = activityList.get(i).getActivityInfo()+","+activityList.get(i).getStaus()+"\n";
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
                addActivity(textInfo.getText().toString(),false);
                writeData();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}