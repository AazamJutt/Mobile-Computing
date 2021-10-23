package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout todoList;
    Button addActivity;
    EditText textInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = findViewById(R.id.todoList);
        addActivity = findViewById(R.id.addActivity);
        addActivity.setOnClickListener(this);
        textInfo = findViewById(R.id.activityInfo);
    }

    public void addActivity(){
        final View todoActivity = getLayoutInflater().inflate(R.layout.todo_activity,null,false);
        TextView activity = todoActivity.findViewById(R.id.activity_info);
        activity.setText(textInfo.getText());
        todoList.addView(todoActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addActivity:
                addActivity();
        }
    }
}