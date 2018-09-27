package com.udacity.gradle.builditbigger.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.FIRST_LAUNCH;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean isFirstLaunch = sharedPreference.getBoolean(FIRST_LAUNCH, true);
        if (isFirstLaunch) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor e = sharedPreference.edit();
                    e.putBoolean(FIRST_LAUNCH, false);
                    e.apply();
                }

            });

            t.start();
        }

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
