package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("zoe", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zoe", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zoe", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zoe", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zoe", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zoe", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zoe", "onDestroy");
    }
}