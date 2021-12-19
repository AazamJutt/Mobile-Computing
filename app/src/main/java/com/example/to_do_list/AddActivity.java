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
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        datePicker = findViewById(R.id.addDate);
        dbHelper = new DbHelper(this);
        activityText = findViewById(R.id.activityInfo);
        cancel = findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(v -> goBack());
        dateText = findViewById(R.id.dateText);
        dateText.setHint(DateFormat.getDateInstance().format(new Date()));
        addActivity = findViewById(R.id.addToList);
        addActivity.setOnClickListener(v -> {
            String date = null;
            if(dateText.getText().toString().equals("")){
                date = DateFormat.getDateInstance().format(new Date());
            }
            else
                date = dateText.getText().toString();
            ActivityModel new_activity = new ActivityModel(activityText.getText().toString(),date,false);
            dbHelper.addActivity(new_activity);
            goBack();
        });
        datePicker.setOnClickListener(v -> {
            DialogFragment picker = new DatePickerFragment();
            picker.show(getSupportFragmentManager(),"date picker");
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