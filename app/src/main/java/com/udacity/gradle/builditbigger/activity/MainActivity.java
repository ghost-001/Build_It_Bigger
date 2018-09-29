package com.udacity.gradle.builditbigger.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.colorDialog.colorDialog;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.BLUE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.COLOR;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.PREFERENCEKEY;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.PURPLE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.YELLOW;


public class MainActivity extends AppCompatActivity implements colorDialog.OnFragmentInteractionListener {

    private String currentColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
        String getColor = prefs.getString(COLOR, null);
        if (getColor != null) {
            String color = prefs.getString(COLOR, PURPLE);
            switch (color) {
                case PURPLE:
                    currentColor = color;
                    setTheme(R.style.AppTheme);
                    break;
                case YELLOW:
                    currentColor = color;
                    setTheme(R.style.CustomYellowAppTheme);
                    break;
                case BLUE:
                    currentColor = color;
                    setTheme(R.style.CustomBlueAppTheme);
                    break;
            }
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_color:
                colorDialog colorDialog = new colorDialog();
                colorDialog.show(getSupportFragmentManager(), "dialog");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(String color) {
        SharedPreferences pf = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        switch (color) {
            case YELLOW:
                if (!color.equals(currentColor)) {
                    editor = pf.edit();
                    editor.clear();
                    editor.putString(COLOR, YELLOW);
                    editor.apply();
                    startActivity(i);
                }
                break;

            case BLUE:
                if (!color.equals(currentColor)) {
                    editor = pf.edit();
                    editor.putString(COLOR, BLUE);
                    editor.apply();
                    startActivity(i);
                }
                break;
            case PURPLE:
                if (!color.equals(currentColor)) {
                    editor = pf.edit();
                    editor.putString(COLOR, PURPLE);
                    editor.apply();
                    startActivity(i);
                }
                break;
        }
    }

}
