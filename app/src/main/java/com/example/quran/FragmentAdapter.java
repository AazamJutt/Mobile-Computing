package com.example.quran;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {
    ArrayList<String> parahs;
    ArrayList<String> surahs;
    ArrayList<Integer> surahVerses;
    ArrayList<Integer> parahVerses;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        parahs = QDH.getParahNames();
        surahs = QDH.getSurahNames();

        surahVerses = new ArrayList<>();
        for (int i=0;i<surahs.size();i++){
            surahVerses.add(QDH.getSurahVerses(i));
        }

        parahVerses = new ArrayList<>();
        for (int i=0;i<parahs.size();i++){
            parahVerses.add(QDH.getParahVerses(i)-1);
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return BySurahFragment.newInstance(surahs,surahVerses);
            case 1:
                return ByParahFragment.newInstance(parahs,parahVerses);
        }
        return BySurahFragment.newInstance(surahs,surahVerses);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
