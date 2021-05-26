package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GameStartFragment extends Fragment {


    public GameStartFragment() {

    }

    public static GameStartFragment newInstance() {
        GameStartFragment fragment = new GameStartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button engButton = view.findViewById(R.id.eng_btn);
        Button rusButton = view.findViewById(R.id.rus_btn);
        engButton.setOnClickListener(this::onEngClick);
        rusButton.setOnClickListener(this::onRusClick);
    }



    private void onEngClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onGameCountDown("English");
        }


    }

    private void onRusClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onGameCountDown("Russian");
        }
    }

}