package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androiddisplaylib.MainActivityLib;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.JOKE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.OPENING;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.joke_button)
    Button jokeButton;
    @BindView(R.id.joke_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.joke_welcome_image)
    ImageView welcomeImage;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        Glide.with(this).load(R.drawable.welcome2).into(welcomeImage);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jokeButton.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                getJokeFromGCE();

            }
        });
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        return root;
    }

    public void getJokeFromGCE() {
        new JokeAsyncTask(new JokeAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Intent intent = new Intent(getActivity(), MainActivityLib.class);
                intent.putExtra(JOKE, output);
                Toast.makeText(getContext(),OPENING,Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                startActivity(intent);
                jokeButton.setEnabled(true);
            }
        }).execute();
    }
}
