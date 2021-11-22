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
    int index;
    public VerseArrayAdapter(Activity context,ArrayList<String> verses,int index) {
        super(context, R.layout.verse_view, verses);
        this.context = context;
        this.verses = verses;
        this.index = index;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        if(verses.get(position).equals(QuranArabicText.QuranArabicText[0])){
            View bismillah=inflater.inflate(R.layout.bismillah_view, null,true);
            return bismillah;
        }
        View singleEntityView=inflater.inflate(R.layout.verse_view, null,true);
        TextView surahName = singleEntityView.findViewById(R.id.verse_name);
        TextView index = singleEntityView.findViewById(R.id.index);
        index.setText("◀"+this.index+"."+(position+1)+"▶");
        surahName.setText(verses.get(position));
        return singleEntityView;
    }
}
