package com.udacity.gradle.builditbigger.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class IntroFragment extends Fragment {

    private static final String RES_LAYOUT = "resLayoutId";
    private int resLayoutId = -1; //default

    public IntroFragment() {
        // Required empty public constructor
    }

    public static IntroFragment newInstance(int resLayoutId) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(RES_LAYOUT, resLayoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resLayoutId = getArguments().getInt(RES_LAYOUT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(resLayoutId, container, false);
    }


}
