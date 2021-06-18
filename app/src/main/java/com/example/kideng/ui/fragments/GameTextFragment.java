package com.example.kideng.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.R;
import com.example.kideng.ui.activities.GameActivity;


public class GameTextFragment extends Fragment {

    private static final String ARG_L = "language";

    private String mLanguage;
    private TextView mTextView;

    public static GameTextFragment newInstance(String language) {
        GameTextFragment fragment = new GameTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_L, language);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLanguage = getArguments().getString(ARG_L);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_text_fragment, container, false);

        mTextView = view.findViewById(R.id.seconds_start_tv);

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(final long l) {
                mTextView.setText(" " + (int) (l * .001f));
            }

            @Override
            public void onFinish() {
                mTextView.setText("");
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity)activity).onGameStart(mLanguage);
                }
            }
        }.start();
        return view;
    }
}