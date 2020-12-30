package com.example.testrestapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.annotation.Nullable;


import java.util.logging.LogRecord;

public class SplashActivity extends Activity {
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
