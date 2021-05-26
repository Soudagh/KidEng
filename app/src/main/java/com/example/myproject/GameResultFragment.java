package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class GameResultFragment extends Fragment {

    private static final String ARG_R = "text";
    private static final String ARG_W = "text";
    private static final String ARG_T = "text";
    private static final String ARG_M = "text";
    private String mT1 = "";
    private String mT2 = "";
    private String mT3 = "";
    private String mT4 = "";

    TextView rAnswer_tv, wAnswer_tv, tAnswer_tv, mark_tv;

    public GameResultFragment() {

    }

    public static GameResultFragment newInstance(int rCounter, int wCounter, int tCounter) {
        GameResultFragment fragment = new GameResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_R, String.valueOf(rCounter));
        args.putString(ARG_W, String.valueOf(wCounter));
        args.putString(ARG_T, String.valueOf(tCounter));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mT1 = getArguments().getString(ARG_R);
            mT2 = getArguments().getString(ARG_W);
            mT3 = getArguments().getString(ARG_T);
            mT4 = getArguments().getString(ARG_M);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_result_fragment, container, false);

        rAnswer_tv = (TextView) view.findViewById(R.id.right_counter_tv);
        wAnswer_tv = (TextView) view.findViewById(R.id.wrong_counter_tv);
        tAnswer_tv = (TextView) view.findViewById(R.id.total_counter_tv);
        mark_tv = (TextView) view.findViewById(R.id.mark_counter_tv);
        rAnswer_tv.setText(mT1);
        wAnswer_tv.setText(mT2);
        tAnswer_tv.setText(mT3);
        return inflater.inflate(R.layout.game_result_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton homeButton = (FloatingActionButton) view.findViewById(R.id.home_btn);
        homeButton.setOnClickListener(this::onHomeClick);
    }

    private void onHomeClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onHome();
        }
    }


}