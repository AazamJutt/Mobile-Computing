package com.example.memeshareapp;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonQueue {

    private RequestQueue requestQueue;
    private static SingletonQueue mInstance;

    private SingletonQueue(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized SingletonQueue getmInstance(Context context){

        if(mInstance == null){
            mInstance = new SingletonQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return  requestQueue;
    }
}