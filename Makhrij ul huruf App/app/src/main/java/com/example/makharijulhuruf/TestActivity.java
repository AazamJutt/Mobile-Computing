package com.example.makharijulhuruf;

import static java.lang.Thread.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    HashMap<String,String> map;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button nextBtn;
    String correctAns;
    TextView harf;
    TextView counter;
    Report report;
    int currentNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        map = new HashMap<>();
        report = new Report();
        currentNumber = 1;
        harf = findViewById(R.id.letter);
        counter = findViewById(R.id.counterText);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        setupMap();

    }

    @Override
    protected void onStart() {
        currentNumber=1;
        report = new Report();
        generateMCQ();
        super.onStart();
    }
    private ArrayList<String> getRandomUniqueOptions(String key){
        List<String> vals = new ArrayList<>(map.values());
        Random rand = new Random();
        ArrayList<String> options = new ArrayList<>();
        options.add(map.get(key));
        for(int i=0;i<3;i++){
            String tempOpt = vals.get(rand.nextInt(vals.size()));
            while(options.contains(tempOpt)){
                tempOpt = vals.get(rand.nextInt(vals.size()));
            }
            options.add(tempOpt);
        }
        Collections.shuffle(options);
        return options;
    }
    private void generateMCQ(){
        if(currentNumber==11){
            Intent intent = new Intent(getApplicationContext(),ReportActivity.class);
            intent.putExtra("Report",report);
            startActivity(intent);
            return;
        }
        String temp = currentNumber +"/10";
        counter.setText(temp);
        option1.setBackgroundColor(Color.GRAY);
        option2.setBackgroundColor(Color.GRAY);
        option3.setBackgroundColor(Color.GRAY);
        option4.setBackgroundColor(Color.GRAY);

        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        option4.setClickable(true);
        String key = getRandomKey();
        ArrayList<String> options = getRandomUniqueOptions(key);
        correctAns = map.get(key);
        harf.setText(key);
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
    }
    private String getRandomKey(){
        List<String> keysAsArray = new ArrayList<>(map.keySet());
        Random rand = new Random();
        return keysAsArray.get(rand.nextInt(keysAsArray.size()));
    }
    private void viewCorrectOption(){
        if(option1.getText().equals(correctAns))
            option1.setBackgroundColor(Color.rgb(74, 194, 45));
        if(option2.getText().equals(correctAns))
            option2.setBackgroundColor(Color.rgb(74, 194, 45));
        if(option3.getText().equals(correctAns))
            option3.setBackgroundColor(Color.rgb(74, 194, 45));
        if(option4.getText().equals(correctAns))
            option4.setBackgroundColor(Color.rgb(74, 194, 45));
    }
    private void disableOptions(){
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }
    private void setupMap(){
        map.put("??","End of Throat");
        map.put("??","End of Throat");
        map.put("??","Middle of Throat");
        map.put("??","Middle of Throat");
        map.put("??","Start of Throat");
        map.put("??","Start of Throat");
        map.put("??","Base of Tongue which is near Uvula touching the mouth roof");
        map.put("??","Portion of Tongue near its base touching the roof of mouth");
        map.put("??","Tongue touching the center of the mouth roof");
        map.put("??","Tongue touching the center of the mouth roof");
        map.put("??","Tongue touching the center of the mouth roof");
        map.put("??","One side of the tongue touching the molar teeth");
        map.put("??","Rounded tip of the tongue touching the base of the");
        map.put("??","Rounded tip of the tongue touching the base of the frontal 6 teeth");
        map.put("??","Rounded tip of the tongue and some portion near it touching the base of the frontal 4 teeth");
        map.put("??","Tip of the tongue touching the base of the front 2 teeth");
        map.put("??","Tip of the tongue touching the base of the front 2 teeth");
        map.put("??","Tip of the tongue touching the base of the front 2 teeth");
        map.put("??","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("??","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("??","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("??","Tip of the tongue comes between the front top and bottom teeth");
        map.put("??","Tip of the tongue comes between the front top and bottom teeth");
        map.put("??","Tip of the tongue comes between the front top and bottom teeth");
        map.put("??","Tip of the two upper jaw teeth touches the inner part of the lower lip");
        map.put("??","Inner part of the both lips touch each other");
        map.put("??","Outer part of both lips touch each other");
        map.put("??","Rounding both lips and not closing the mouth");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextBtn:
                generateMCQ();
                break;
            default:
                if(((Button)v).getText().equals(correctAns)){
                    v.setBackgroundColor(Color.rgb(74, 194, 45));
                    report.addRecord(harf.getText().toString(),correctAns,((Button) v).getText().toString(),true);
                    disableOptions();
                }
                else {
                    v.setBackgroundColor(Color.rgb(191, 48, 29));
                    viewCorrectOption();
                    report.addRecord(harf.getText().toString(),correctAns,((Button) v).getText().toString(),false);
                    disableOptions();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentNumber++;
                        generateMCQ();
                    }
                }, 2000);
                break;
        }
    }
}