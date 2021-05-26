package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class GameTextFragment extends Fragment {

    TextView textView;

    public GameTextFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_text_fragment, container, false);

        textView = (TextView) view.findViewById(R.id.seconds_start_tv);

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(final long l) {
                ;
                textView.setText(" " + (int) (l * .001f));
            }

            @Override
            public void onFinish() {
                textView.setText("");
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity)activity).onGameStart();
                }
            }
        }.start();
        return view;
    }
}