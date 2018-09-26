package com.example.androiddisplaylib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Locale;

import static android.view.View.GONE;
import static com.example.androiddisplaylib.libConstants.libConstants.BLUE;
import static com.example.androiddisplaylib.libConstants.libConstants.ERROR;
import static com.example.androiddisplaylib.libConstants.libConstants.FAILED;
import static com.example.androiddisplaylib.libConstants.libConstants.JOKE;
import static com.example.androiddisplaylib.libConstants.libConstants.LANGUAGE_NOT_SUPPORTED;
import static com.example.androiddisplaylib.libConstants.libConstants.PREFERENCEKEY;
import static com.example.androiddisplaylib.libConstants.libConstants.PURPLE;
import static com.example.androiddisplaylib.libConstants.libConstants.YELLOW;

public class MainActivityLib extends AppCompatActivity {
    TextView jokeTv;
    ImageButton jokeButton;
    ImageView imageView;
    CardView jokeCard;

    String joke;
    Boolean checkJoke = true;
    TextToSpeech textToSpeech;

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
        jokeButton = findViewById(R.id.lib_joke_button);


        Glide.with(this).load(R.drawable.smiley).into(imageView);
        Intent intent = getIntent();
        joke = intent.getStringExtra(JOKE);


        jokeTv.setTextColor(Color.WHITE);
        jokeTv.setText(joke);
        if (joke == null) {
            imageView.setVisibility(GONE);
            jokeTv.setText(FAILED);
            checkJoke = false;
            jokeButton.setVisibility(GONE);
        }

        if (checkJoke) {
            setTextToSpeech();
        }


    }

    public void setTextToSpeech() {
        if (checkJoke) {
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i == TextToSpeech.SUCCESS) {
                        int result = textToSpeech.setLanguage(Locale.ENGLISH);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getApplicationContext(), LANGUAGE_NOT_SUPPORTED, Toast.LENGTH_SHORT).show();
                        } else {
                            textToSpeech.setPitch(0.6f);
                            textToSpeech.setSpeechRate(1.0f);
                        }

                    }
                }
            });
            jokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    speakText();
                }
            });
        }
    }
    public void speakText() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(joke, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(joke, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_lib, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            if (checkJoke) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(MainActivityLib.this)
                        .setType("text/plain")
                        .setText(joke)
                        .getIntent(), JOKE));
            } else {
                Toast.makeText(this, ERROR, Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    protected void onPostResume() {
        setTextToSpeech();
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
