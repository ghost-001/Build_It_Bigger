package com.example.androiddisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MainActivityLib extends AppCompatActivity {
    TextView jokeTv;
    ImageView imageView;
    TextView text;
    CardView jokeCard;

    String joke;
    Boolean checkJoke = true;
    public static final String JOKE = "joke";
    public static final String FAILED = "Failed to fetch Joke, try again";
    public static final String ERROR = "No Joke to share :(";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);
        jokeTv = findViewById(R.id.lib_joke_text);
        imageView = findViewById(R.id.lib_image);
        text = findViewById(R.id.lib_text);
        jokeCard = findViewById(R.id.cardView);


        Glide.with(this).load(R.drawable.smiley).into(imageView);
        Intent intent = getIntent();
        joke = intent.getStringExtra(JOKE);
        jokeTv.setText(joke);
        if(joke == null){
            imageView.setVisibility(GONE);
            text.setVisibility(GONE);
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
