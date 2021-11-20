package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> parahs;
    ArrayList<String> surahs;
    ArrayList<Integer> verseCount;

    TabLayout tabs;
    ListView surahList;
    QDH quranIndexes;
    QuranArabicText quranData;
    ArrayAdapter<String> parahArrayAdapter;
    SurahArrayAdapter surahArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quranIndexes = new QDH();
        quranData = new QuranArabicText();

        parahs = quranIndexes.getParahNames();
        surahs = quranIndexes.getSurahNames();
        verseCount = new ArrayList<>();

        for (int i=0;i<surahs.size();i++){
            verseCount.add(quranIndexes.getSurahVerses(i));
        }

        surahList = findViewById(R.id.listView);
        tabs = findViewById(R.id.tabLayout);
        parahArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,parahs);
        surahArrayAdapter = new SurahArrayAdapter(this,surahs,verseCount);
        surahList.setAdapter(surahArrayAdapter);

        surahList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this,VerseActivity.class);
            ArrayList<String> verses = new ArrayList<>(Arrays.asList(quranData.GetData(quranIndexes.getSurahStart(position)-1,quranIndexes.getSurahStart(position+1)-1)));
            intent.putStringArrayListExtra("verses",verses);
            startActivity(intent);
        });
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        surahList.setAdapter(parahArrayAdapter);
                    case 1:
                        surahList.setAdapter(surahArrayAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                surahList.setAdapter(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });

    }
}