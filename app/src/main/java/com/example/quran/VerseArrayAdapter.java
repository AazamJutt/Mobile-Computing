package com.example.quran;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VerseArrayAdapter extends ArrayAdapter<String> {
    Activity context;
    ArrayList<String> verses;
    public VerseArrayAdapter(Activity context,ArrayList<String> verses) {
        super(context, R.layout.verse_view, verses);
        this.context = context;
        this.verses = verses;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View singleEntityView=inflater.inflate(R.layout.verse_view, null,true);
        TextView surahName = singleEntityView.findViewById(R.id.verse_name);
        surahName.setText(verses.get(position));
        return singleEntityView;
    }
}
