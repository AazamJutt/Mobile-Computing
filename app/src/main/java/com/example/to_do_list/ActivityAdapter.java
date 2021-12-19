package com.example.to_do_list;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class ActivityAdapter extends ArrayAdapter<ActivityModel> {
    private final Activity  context;
    ArrayList<ActivityModel> activityModels;
    DbHelper dbHelper;
    public ActivityAdapter(Activity context, ArrayList<ActivityModel> activities,DbHelper dbHelper) {
        super(context,R.layout.todo_activity,activities);
        this.context = context;
        this.activityModels = activities;
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActivityModel currActivity = activityModels.get(position);
        LayoutInflater inflater=context.getLayoutInflater();
        View singleEntityView=inflater.inflate(R.layout.todo_activity, null,true);
        TextView info = singleEntityView.findViewById(R.id.activity_info);
        TextView date = singleEntityView.findViewById(R.id.date);
        CheckBox status = singleEntityView.findViewById(R.id.activity_status);
        status.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                currActivity.setStaus(true);
                info.setPaintFlags(info.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                dbHelper.updateActivity(currActivity);
                notifyDataSetChanged();
            }
            else{
                currActivity.setStaus(false);
                info.setPaintFlags(0);
                dbHelper.updateActivity(currActivity);
                notifyDataSetChanged();
            }
        });
        ImageView remove = singleEntityView.findViewById(R.id.image_remove);
        remove.setOnClickListener(v -> {
            dbHelper.removeActivity(currActivity);
            activityModels.remove(currActivity);
            notifyDataSetChanged();
        });
        info.setText(currActivity.getActivityInfo());
        date.setText(currActivity.getDate());
        status.setChecked(currActivity.getStaus());

        return singleEntityView;
    }
}
