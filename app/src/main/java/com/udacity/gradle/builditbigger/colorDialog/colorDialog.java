package com.udacity.gradle.builditbigger.colorDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.activity.MainActivity;

import static com.udacity.gradle.builditbigger.appConstant.AppConstants.GREEN;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.GREY;
import static com.udacity.gradle.builditbigger.appConstant.AppConstants.PURPLE;

public class colorDialog extends DialogFragment {


    RelativeLayout relativeLayoutColor1;
    RelativeLayout relativeLayoutColor2;
    RelativeLayout relativeLayoutColor3;

    private OnFragmentInteractionListener mListener;

    public colorDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_color_dialog, null);
        relativeLayoutColor1 = v.findViewById(R.id.relativeLayout1);
        relativeLayoutColor2 = v.findViewById(R.id.relativeLayout2);
        relativeLayoutColor3 = v.findViewById(R.id.relativeLayout3);
        relativeLayoutColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(GREY);
                dismiss();
            }
        });
        relativeLayoutColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(GREEN);
                dismiss();
            }
        });
        relativeLayoutColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(PURPLE);
                dismiss();
            }
        });
        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (MainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "ERROR");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String color);
    }
}
