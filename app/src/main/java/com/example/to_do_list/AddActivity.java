package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button datePicker;
    Button addActivity;
    EditText dateText;
    EditText activityText;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        datePicker = findViewById(R.id.addDate);
        activityText = findViewById(R.id.activityInfo);
        cancel = findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        dateText = findViewById(R.id.dateText);
        dateText.setHint(DateFormat.getDateInstance().format(new Date()));
        addActivity = findViewById(R.id.addToList);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity new_activity = new Activity(activityText.getText().toString(),dateText.getText().toString(),false);
                MainActivity.activityList.add(new_activity);
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(MainActivity.FIELANAME,MODE_PRIVATE);
                    for (Activity activity:MainActivity.activityList) {
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
                System.out.println(MainActivity.activityList);
                goBack();
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getSupportFragmentManager(),"date picker");
            }
        });
    }

    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        this.dateText.setText(date);
    }
}