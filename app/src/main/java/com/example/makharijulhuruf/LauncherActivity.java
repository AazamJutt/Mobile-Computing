package com.example.makharijulhuruf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {

    Button goToGitBtn;
    Button launchAppBtn;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goToGitBtn = findViewById(R.id.goToRepoBtn);
        goToGitBtn.setOnClickListener(v -> {
            String url = "https://github.com/AazamJutt/Mobile-Computing/tree/Makharij-ul-Huruf";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        launchAppBtn = findViewById(R.id.launchAppBtn);
        launchAppBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_repo:
                String url = "https://github.com/AazamJutt/Mobile-Computing/tree/Makharij-ul-Huruf";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.opt_share:
                url = "https://github.com/AazamJutt/Mobile-Computing/tree/Makharij-ul-Huruf";
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,url);
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            case R.id.opt_exit:
                finish();
                break;
        }
        return true;
    }
}