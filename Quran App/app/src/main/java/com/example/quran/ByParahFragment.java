package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ByParahFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ByParahFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "parah";
    private static final String ARG_PARAM2 = "parahVerseCount";

    // TODO: Rename and change types of parameters
    private ArrayList<String> parahs;
    private ArrayList<Integer> verseCounts;

    ListView parahList;

    public ByParahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BySurahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ByParahFragment newInstance(ArrayList<String> parahs,ArrayList<Integer> verseCounts) {
        ByParahFragment fragment = new ByParahFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, parahs);
        args.putIntegerArrayList(ARG_PARAM2, verseCounts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parahs = getArguments().getStringArrayList(ARG_PARAM1);
            verseCounts = getArguments().getIntegerArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_by_parah, container, false);
        parahList = layout.findViewById(R.id.parahList);
        parahList.setAdapter(new SurahArrayAdapter(getActivity(), parahs, verseCounts));
        parahList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(),VerseActivity.class);
            ArrayList<String> verses = QuranArabicText.GetData(QDH.getParahStart(position)-1,QDH.getParahStart(position)+QDH.getParahVerses(position)-1);
            intent.putStringArrayListExtra("verses",verses);
            intent.putExtra("index",position);
            startActivity(intent);
        });

        return  layout;
    }
}