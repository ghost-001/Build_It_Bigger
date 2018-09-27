package com.udacity.gradle.builditbigger.free.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.androiddisplaylib.MainActivityLib;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.aysncTask.JokeAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.JOKE;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.OPENING;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.joke_button)
    Button jokeButton;
    @BindView(R.id.joke_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.joke_welcome_image)
    ImageView welcomeImage;

    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);


        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        Glide.with(this).load(R.drawable.welcome1).into(welcomeImage);
        handleAnimation(welcomeImage);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jokeButton.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }else{
                    getJokeFromGCE();
                }


            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                getJokeFromGCE();
            }

        });

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        //interstitial Ad


        return root;
    }

    public void handleAnimation(View view) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(1)
                .playOn(welcomeImage);

    }
    public void getJokeFromGCE(){
        new JokeAsyncTask(new JokeAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Intent intent = new Intent(getActivity(),MainActivityLib.class);
                intent.putExtra(JOKE,output);
                Toast.makeText(getContext(),OPENING,Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                startActivity(intent);
                jokeButton.setEnabled(true);
            }
        }).execute();
    }
}
