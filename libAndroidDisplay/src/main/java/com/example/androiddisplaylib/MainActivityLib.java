package com.example.androiddisplaylib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.view.View.GONE;
import static com.example.androiddisplaylib.libConstants.libConstants.BLUE;
import static com.example.androiddisplaylib.libConstants.libConstants.ERROR;
import static com.example.androiddisplaylib.libConstants.libConstants.FAILED;
import static com.example.androiddisplaylib.libConstants.libConstants.JOKE;
import static com.example.androiddisplaylib.libConstants.libConstants.PREFERENCEKEY;
import static com.example.androiddisplaylib.libConstants.libConstants.PURPLE;
import static com.example.androiddisplaylib.libConstants.libConstants.YELLOW;

public class MainActivityLib extends AppCompatActivity {
    TextView jokeTv;
    ImageView imageView;
    CardView jokeCard;

    String joke;
    Boolean checkJoke = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(PREFERENCEKEY, MODE_PRIVATE);
        String color = prefs.getString("color", PURPLE);
        switch (color) {
            case PURPLE:
                setTheme(R.style.AppTheme);
                break;
            case YELLOW:
                setTheme(R.style.CustomYellowAppTheme);
                break;
            case BLUE:
                setTheme(R.style.CustomBlueAppTheme);
                break;

        }
        setContentView(R.layout.activity_main_lib);
        jokeTv = findViewById(R.id.lib_joke_text);
        imageView = findViewById(R.id.lib_image);
        jokeCard = findViewById(R.id.cardView);


        Glide.with(this).load(R.drawable.smiley).into(imageView);
        Intent intent = getIntent();
        joke = intent.getStringExtra(JOKE);


        jokeTv.setTextColor(Color.WHITE);
        jokeTv.setText(joke);
        if(joke == null){
            imageView.setVisibility(GONE);
            jokeTv.setText(FAILED);
            checkJoke = false;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_lib,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            if(checkJoke) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, joke);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }else{
                Toast.makeText(this,ERROR,Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
