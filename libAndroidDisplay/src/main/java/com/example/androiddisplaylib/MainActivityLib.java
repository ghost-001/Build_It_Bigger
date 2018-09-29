package com.example.androiddisplaylib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.androiddisplaylib.libConstants.libConstants;

import java.util.Locale;
import java.util.Random;

import static android.view.View.GONE;
import static com.example.androiddisplaylib.libConstants.libConstants.COLOR;
import static com.example.androiddisplaylib.libConstants.libConstants.ERROR;
import static com.example.androiddisplaylib.libConstants.libConstants.FAILED;
import static com.example.androiddisplaylib.libConstants.libConstants.GREEN;
import static com.example.androiddisplaylib.libConstants.libConstants.GREY;
import static com.example.androiddisplaylib.libConstants.libConstants.JOKE;
import static com.example.androiddisplaylib.libConstants.libConstants.LANGUAGE_NOT_SUPPORTED;
import static com.example.androiddisplaylib.libConstants.libConstants.PREFERENCEKEY;
import static com.example.androiddisplaylib.libConstants.libConstants.PURPLE;

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
        String color = prefs.getString(COLOR, GREY);
        switch (color) {
            case PURPLE:
                setTheme(R.style.CustomPurpleAppTheme);
                break;
            case GREEN:
                setTheme(R.style.CustomGreenAppTheme);
                break;
            case GREY:
                setTheme(R.style.AppTheme);
                break;


        }
        setContentView(R.layout.activity_main_lib);
        jokeTv = findViewById(R.id.lib_joke_text);
        imageView = findViewById(R.id.lib_image);
        jokeCard = findViewById(R.id.cardView);
        jokeButton = findViewById(R.id.lib_joke_button);

        final int min = 0;
        final int max = 3;
        final int randomIndex = new Random().nextInt(max - min) + min;

        Glide.with(this).load(libConstants.images[randomIndex]).into(imageView);
        Intent intent = getIntent();
        joke = intent.getStringExtra(JOKE);


        jokeTv.setTextColor(Color.WHITE);
        jokeTv.setText(joke);
        if (joke == null) {
            imageView.setVisibility(GONE);
            joke = FAILED;
            jokeTv.setText(FAILED);
            checkJoke = false;
            jokeButton.setVisibility(GONE);

        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jokeCard.setVisibility(View.VISIBLE);
                if (checkJoke)
                    handleCardAnimation(jokeCard);
            }
        }, 700);

        if (checkJoke) {
            setTextToSpeech();
            jokeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    speakText();
                }
            });
            handleAnimation(imageView);
        }

    }

    public void handleAnimation(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(1)
                .playOn(view);
        YoYo.with(Techniques.Bounce)
                .duration(500)
                .repeat(3)
                .playOn(view);

    }

    public void handleCardAnimation(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .repeat(1)
                .playOn(view);
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(2)
                .playOn(view);

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
                            Log.i(libConstants.TAG, LANGUAGE_NOT_SUPPORTED);
                        } else {
                            textToSpeech.setPitch(1.0f);
                            textToSpeech.setSpeechRate(1.0f);
                        }

                    }
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
        if (item.getItemId() == R.id.action_share) {
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
