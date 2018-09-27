package com.udacity.gradle.builditbigger.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.fragment.IntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(IntroFragment.newInstance(R.layout.intro_slide1));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide2));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide3));
        addSlide(IntroFragment.newInstance(R.layout.intro_slide4));

        //setSeparatorColor(Color.parseColor("#2196F3"));
        getSupportActionBar().hide();
        showStatusBar(false);
        showSkipButton(true);
        setDepthAnimation();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }
}

