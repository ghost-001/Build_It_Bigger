package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.udacity.gradle.builditbigger.colorDialog.colorDialog;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.BLUE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.PREFERENCEKEY;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.PURPLE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.YELLOW;


public class MainActivity extends AppCompatActivity implements colorDialog.OnFragmentInteractionListener {

    String currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
        String getColor = prefs.getString("color", null);
        if (getColor != null) {
            String color = prefs.getString("color", PURPLE);
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
        switch (color) {
            case YELLOW:

                if (!color.equals(currentColor)) {
                    SharedPreferences pf = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pf.edit();
                    editor.clear();
                    editor.putString("color", YELLOW);
                    editor.apply();
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                break;

            case BLUE:
                if (!color.equals(currentColor)) {
                    SharedPreferences pf2 = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pf2.edit();
                    editor1.putString("color", BLUE);
                    editor1.apply();
                    Intent i2 = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i2);
                    break;
                }
            case PURPLE:
                if (!color.equals(currentColor)) {
                    SharedPreferences pf3 = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = pf3.edit();
                    editor2.putString("color", PURPLE);
                    editor2.apply();
                    Intent i3 = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i3);
                    break;
                }
        }
    }

}
