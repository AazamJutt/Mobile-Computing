package com.example.makharijulhuruf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.BidiFormatter;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView letter;
    String[] letters = {"ع","ح"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //letter = findViewById(R.id.letter);
        //letter.setText(letters[0]);
    }
}