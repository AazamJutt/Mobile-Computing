package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class VerseActivity extends AppCompatActivity {

    ListView verses;
    ArrayList<String> versesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verses);

        verses = findViewById(R.id.verses);
        versesList = getIntent().getStringArrayListExtra("verses");

        verses.setAdapter(new VerseArrayAdapter(this,versesList));

    }
}