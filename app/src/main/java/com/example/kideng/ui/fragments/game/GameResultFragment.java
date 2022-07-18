package com.example.kideng.ui.fragments.game;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.GameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class GameResultFragment extends Fragment {

    private static final String ARG_Int1 = "1";
    private static final String ARG_Int2 = "2";
    private static final String ARG_Int3 = "3";
    private static final String ARG_L = "language";
    private static final String ARG_D = "duration";
    private static final String ARG_G = "goal";
    private static final String ARG_LIST = "themeList";

    private String mRCount;
    private String mWCount;
    private String mTCount;
    private String translate;
    private String duration;
    private String goal;
    private ArrayList<Integer> themeList;

    public static GameResultFragment newInstance(String rCounter, String wCounter, String tCounter, String translate, String goal, String duration,  ArrayList<Integer> themeList) {
        GameResultFragment fragment = new GameResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Int1, rCounter);
        args.putString(ARG_Int2, wCounter);
        args.putString(ARG_Int3, tCounter);
        args.putString(ARG_L, translate);
        args.putString(ARG_D, duration);
        args.putString(ARG_G, goal);
        args.putIntegerArrayList(ARG_LIST, themeList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRCount = getArguments().getString(ARG_Int1);
            mWCount = getArguments().getString(ARG_Int2);
            mTCount = getArguments().getString(ARG_Int3);
            translate = getArguments().getString(ARG_L);
            duration = getArguments().getString(ARG_D);
            goal = getArguments().getString(ARG_G);
            themeList = getArguments().getIntegerArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_result_fragment, container, false);

        TextView rAnswer_tv = view.findViewById(R.id.right_counter_tv);
        TextView wAnswer_tv = view.findViewById(R.id.wrong_counter_tv);
        TextView tAnswer_tv = view.findViewById(R.id.total_counter_tv);

        rAnswer_tv.setText(mRCount);
        wAnswer_tv.setText(mWCount);
        tAnswer_tv.setText(mTCount);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton homeButton = view.findViewById(R.id.home_btn);
        FloatingActionButton againButton = view.findViewById(R.id.again_btn);
        FloatingActionButton changeModeButton = view.findViewById(R.id.changeMode_btn);
        homeButton.setOnClickListener(this::onHomeClick);
        againButton.setOnClickListener(this::onAgainClick);
        changeModeButton.setOnClickListener(this::onChangeClick);
    }

    private void onChangeClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onChange();
        }
    }

    private void onAgainClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onAgain(translate, goal, duration, themeList);
        }
    }

    private void onHomeClick(View view) {
        Activity activity = getActivity();
        if (activity instanceof GameActivity) {
            ((GameActivity)activity).onHome();
        }
    }
}