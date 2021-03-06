package com.hobarb.gux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    private static final long SPLASH_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               onFirstRun();
            }
        }, SPLASH_TIME);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //onFirstRun();

    }

    public void onFirstRun(){

        boolean ifFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("ifFirstRun", true);

        if(ifFirstRun)
        {

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("ifFirstRun", false).apply();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }
        else
        {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

    }
}
