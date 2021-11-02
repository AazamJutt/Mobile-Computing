package com.example.to_do_list;

import java.util.Calendar;

public class Activity {
    String activityInfo;
    String date;
    boolean status;

    public Activity(String activityInfo,String date, boolean staus) {
        this.activityInfo = activityInfo;
        this.date = date;
        this.status = staus;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public boolean getStaus() {
        return status;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public void setStaus(boolean staus) {
        this.status = staus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
