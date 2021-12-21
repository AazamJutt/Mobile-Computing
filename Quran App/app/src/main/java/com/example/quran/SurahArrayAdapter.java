package com.example.quran;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class SurahArrayAdapter extends ArrayAdapter<String> {
    private final Activity  context;
    ArrayList<String> surahList;
    ArrayList<Integer> verseCountList;
    public SurahArrayAdapter(Activity context,ArrayList<String> surahList,ArrayList<Integer> verseCountList) {
        super(context,R.layout.surah_view, surahList);
        this.context = context;
        this.surahList = surahList;
        this.verseCountList = verseCountList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View singleEntityView=inflater.inflate(R.layout.surah_view, null,true);
        TextView surahName = singleEntityView.findViewById(R.id.surah_name);
        TextView verseCount = singleEntityView.findViewById(R.id.verseCount);
        verseCount.setText("Verses: "+verseCountList.get(position));
        surahName.setText(surahList.get(position));
        return singleEntityView;
    }
}
