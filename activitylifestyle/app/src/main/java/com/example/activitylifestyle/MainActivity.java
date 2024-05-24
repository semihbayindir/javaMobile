package com.example.activitylifestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("sevval","onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("sevval","onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("sevval","onPause");
    }
}