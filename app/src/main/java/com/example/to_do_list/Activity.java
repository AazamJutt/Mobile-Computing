package com.example.to_do_list;

public class Activity {
    String activityInfo;
    boolean staus;

    public Activity(String activityInfo, boolean staus) {
        this.activityInfo = activityInfo;
        this.staus = staus;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public boolean getStaus() {
        return staus;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public void setStaus(boolean staus) {
        this.staus = staus;
    }
}
