package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.colorDialog.colorDialog;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.BLUE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.YELLOW;


public class MainActivity extends AppCompatActivity implements colorDialog.OnFragmentInteractionListener {

    String currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("FUCK", MODE_PRIVATE);
        String getColor = prefs.getString("color", null);
        if (getColor != null) {
            String color = prefs.getString("color", YELLOW);
            switch (color) {
                case YELLOW:
                    currentColor = color;
                    setTheme(R.style.AppTheme);
                    break;
                case BLUE:
                    currentColor = color;
                    setTheme(R.style.CustomAppTheme);
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

        Toast.makeText(this, color + " CLICKED", Toast.LENGTH_SHORT).show();
        switch (color) {
            case YELLOW:

                if (!color.equals(currentColor)) {
                    SharedPreferences pf = getApplicationContext().getSharedPreferences("FUCK", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pf.edit();
                    editor.clear();
                    editor.putString("color", YELLOW);
                    editor.apply();
                    Toast.makeText(this, color + " SAVED", Toast.LENGTH_SHORT).show();
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                break;

            case BLUE:
                if (!color.equals(currentColor)) {
                    SharedPreferences pf2 = getApplicationContext().getSharedPreferences("FUCK", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pf2.edit();
                    editor1.putString("color", BLUE);
                    Toast.makeText(this, color + " SAVED", Toast.LENGTH_SHORT).show();
                    editor1.apply();
                    Intent i2 = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i2);
                    break;
                }
        }
    }

}
