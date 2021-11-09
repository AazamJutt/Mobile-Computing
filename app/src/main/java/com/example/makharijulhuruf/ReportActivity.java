package com.example.makharijulhuruf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    TextView total;
    TextView correct;
    TextView wrong;
    Report report;
    LinearLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        report = intent.getParcelableExtra("Report");
        total = findViewById(R.id.totalTxt);
        view = findViewById(R.id.view);
        String temp = "Total: "+report.getTotal();
        total.setText(temp);
        temp = "Correct: "+ report.getCorrect();
        correct = findViewById(R.id.correctTxt);
        correct.setText(temp);
        wrong = findViewById(R.id.wrongText);
        temp = "Wrong: "+report.getInCorrect();
        wrong.setText(temp);
    }
    private void setupScrollView(){
        final View image = getLayoutInflater().inflate(R.layout.report_question,null,false);
        TextView question = image.findViewById(R.id.quesTxt);
        TextView answer = image.findViewById(R.id.ansTxt);
        view.addView(image);
    }
}