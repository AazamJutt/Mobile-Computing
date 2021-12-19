package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counter;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=(TextView) findViewById(R.id.counter);
        seekBar =(SeekBar) findViewById(R.id.seekBar);
    }

    public void increment(View view) {
        int newVal = Integer.parseInt(counter.getText().toString())+1;
        counter.setText(Integer.toString(newVal));
        seekBar.setProgress(newVal);
    }

    public void decrement(View view) {
        int newVal = Integer.parseInt(counter.getText().toString())-1;
        counter.setText(Integer.toString(newVal));
        seekBar.setProgress(newVal);
    }
    public void reset(View view) {
        counter.setText("0");
        seekBar.setProgress(0);
    }
}