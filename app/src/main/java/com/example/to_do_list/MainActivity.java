package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.ScientificNotation;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        readData("todoList.txt");
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

    public void readData(String filename){
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, filename);
        if(!file.exists())
            return;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                Activity activity = new Activity(fields[0], Boolean.parseBoolean(fields[1]));
                activityList.add(activity);
                addActivity(activity.getActivityInfo(),activity.getStaus());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeData(String filename) throws IOException {
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path,filename);
        if(!file.exists())
            file.createNewFile();
        PrintWriter writer = new PrintWriter(file);
        for(int i=0;i<activityList.size();i++){
            writer.println(activityList.get(i).getActivityInfo()+","+activityList.get(i).getStaus());
        }
        writer.close();
    }
    @Override
    protected void onDestroy() {
        try {
            writeData("todoList.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

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