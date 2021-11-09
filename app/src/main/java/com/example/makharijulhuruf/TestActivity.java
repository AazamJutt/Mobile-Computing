package com.example.makharijulhuruf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class TestActivity extends AppCompatActivity {

    HashMap<String,String> map;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView harf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        map = new HashMap<>();
        harf = findViewById(R.id.letter);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        setupMap();

    }

    @Override
    protected void onStart() {
        harf.setText(getRandomKey());
        getRandomOptions();
        super.onStart();
    }
    private void getRandomOptions(){
        List<String> vals = new ArrayList<>(map.values());
        Random rand = new Random();
        ArrayList<String> options = new ArrayList<>();
        options.add(map.get(harf.getText().toString()));
        options.add(vals.get(rand.nextInt(vals.size())));
        options.add(vals.get(rand.nextInt(vals.size())));
        options.add(vals.get(rand.nextInt(vals.size())));
        Collections.shuffle(options);
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
    private void setupMap(){
        map.put("أ","End of Throat");
        map.put("ہ","End of Throat");
        map.put("ع","Middle of Throat");
        map.put("ح","Middle of Throat");
        map.put("غ","Start of Throat");
        map.put("خ","Start of Throat");
        map.put("ق","Base of Tongue which is near Uvula touching the mouth roof");
        map.put("ک","Portion of Tongue near its base touching the roof of mouth");
        map.put("ی","Tongue touching the center of the mouth roof");
        map.put("ج","Tongue touching the center of the mouth roof");
        map.put("ش","Tongue touching the center of the mouth roof");
        map.put("ض","One side of the tongue touching the molar teeth");
        map.put("ل","Rounded tip of the tongue touching the base of the");
        map.put("ن","Rounded tip of the tongue touching the base of the frontal 6 teeth");
        map.put("ر","Rounded tip of the tongue and some portion near it touching the base of the frontal 4 teeth");
        map.put("ط","Tip of the tongue touching the base of the front 2 teeth");
        map.put("د","Tip of the tongue touching the base of the front 2 teeth");
        map.put("ت","Tip of the tongue touching the base of the front 2 teeth");
        map.put("ث","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("ذ","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("ظ","Tip of the tongue touching the tip of the frontal 2 teeth");
        map.put("س","Tip of the tongue comes between the front top and bottom teeth");
        map.put("ز","Tip of the tongue comes between the front top and bottom teeth");
        map.put("ص","Tip of the tongue comes between the front top and bottom teeth");
        map.put("ف","Tip of the two upper jaw teeth touches the inner part of the lower lip");
        map.put("ب","Inner part of the both lips touch each other");
        map.put("م","Outer part of both lips touch each other");
        map.put("و","Rounding both lips and not closing the mouth");

    }
}