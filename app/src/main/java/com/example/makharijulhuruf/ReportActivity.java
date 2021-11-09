package com.example.makharijulhuruf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    TextView total;
    TextView correct;
    TextView wrong;
    TextView percentage;
    Report report;
    LinearLayout view;
    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        report = intent.getParcelableExtra("Report");
        total = findViewById(R.id.totalTxt);
        view = findViewById(R.id.view);
        share = findViewById(R.id.shareBtn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, createReport() );
                startActivity(Intent.createChooser(intent2, "Share via"));
            }
        });
        percentage = findViewById(R.id.percentage);
        String temp = "Total: "+report.getTotal();
        total.setText(temp);
        temp = "Correct: "+ report.getCorrect();
        correct = findViewById(R.id.correctTxt);
        correct.setText(temp);
        wrong = findViewById(R.id.wrongText);
        temp = "Wrong: "+report.getInCorrect();
        wrong.setText(temp);
        setupScrollView();
    }
    private String createReport(){
        StringBuilder str = new StringBuilder();
        str.append("Hey There, I attempted a test in Makharij ul Huruf app.\n");
        str.append("with a Score: "+report.getCorrect()+"\n");
        str.append("Out of: "+report.getTotal()+"\n");
        return str.toString();

    }
    private void setupScrollView(){
        ArrayList<String> questions = report.getQuestion();
        ArrayList<String> answers = report.getAnswers();
        ArrayList<String> chosen = report.getChosen();
        int percent = (report.getCorrect()*100)/report.getTotal();
        percentage.setText(Integer.toString(percent)+"%");
        for(int i=0;i<questions.size();i++) {
            final View reportQues = getLayoutInflater().inflate(R.layout.report_question, null, false);
            TextView question = reportQues.findViewById(R.id.quesTxt);
            String temp = "Question: "+questions.get(i);
            question.setText(temp);
            TextView answer = reportQues.findViewById(R.id.ansTxt);
            String choice = chosen.get(i);
            answer.setText("Your Answer: "+choice);
            TextView correct = reportQues.findViewById(R.id.correctAns);
            String right = answers.get(i);
            correct.setText("Correct Answer: "+right);
            ConstraintLayout layout = reportQues.findViewById(R.id.layout);
            if(choice.equals(right)) {
                layout.setBackground(getDrawable(R.drawable.round_green));
            }
            else {
                layout.setBackground(getDrawable(R.drawable.round_red));
            }
            view.addView(reportQues);
            final View divider = getLayoutInflater().inflate(R.layout.divider, null, false);
            view.addView(divider);
        }
    }
}