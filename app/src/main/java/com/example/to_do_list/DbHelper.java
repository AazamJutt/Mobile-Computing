package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String ACTIVITY_INFO = "Info";
    public static final String ACTIVITY_DATE = "Date";
    public static final String ACTIVE_STATUS = "Status";
    public static final String ACTIVITY_ID = "ActivityID";
    public static final String ACTIVITY_TABLE = "Activity";

    public DbHelper(@Nullable Context context) {
        super(context, "ActivityDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTableSTatementOne = "CREATE TABLE CustTable(ActivityID Integer PRIMARY KEY AUTOINCREMENT, " + Activity_NAME_FIRST + " Text, ActivityAge Int, ActiveActivity BOOL) ";
        String createTableSTatement = "CREATE TABLE " + ACTIVITY_TABLE + "(" + ACTIVITY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + ACTIVITY_INFO + " Text, " + ACTIVITY_DATE + " Text, " + ACTIVE_STATUS + " BOOL) ";
        db.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_TABLE);
        onCreate(db);
    }

    public void addActivity(ActivityModel ActivityModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(ACTIVITY_INFO, ActivityModel.getActivityInfo());
        cv.put(ACTIVITY_DATE, ActivityModel.getDate());
        cv.put(ACTIVE_STATUS, ActivityModel.getStaus());
        db.insert(ACTIVITY_TABLE, null, cv);
        db.close();
    }

    public void removeActivity(ActivityModel activityModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ACTIVITY_TABLE,ACTIVITY_ID+"= ?",new String[]{Integer.toString(activityModel.getId())});
    }

    public void updateActivity(ActivityModel activityModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACTIVE_STATUS,activityModel.getStaus()?String.valueOf(1):String.valueOf(0));
        db.update(ACTIVITY_TABLE,cv,ACTIVITY_ID+"= ?",new String[]{Integer.toString(activityModel.getId())});

    }

    public ArrayList<ActivityModel> getAllActivities() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + ACTIVITY_TABLE, null);

        ArrayList<ActivityModel> ActivityArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                ActivityModel activityModel = new ActivityModel(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getInt(3) == 1);
                activityModel.setId(cursorCourses.getInt(0));
                ActivityArrayList.add(activityModel);
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return ActivityArrayList;
    }
}
