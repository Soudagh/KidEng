package com.example.kideng.ui.fragments.game;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.R;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.GameActivity;

import java.util.ArrayList;
import java.util.List;


public class GameTextFragment extends Fragment {

    private static final String ARG_T = "translate";
    private static final String ARG_G = "goal";
    private static final String ARG_D = "duration";
    private static final String ARG_LIST = "themeList";

    private String translate;
    private String goal;
    private String duration;
    private ArrayList<Integer> themeList;
    private TextView mTextView;

    public static GameTextFragment newInstance(String translate, String goal, String duration, ArrayList<Integer> themeList) {
        GameTextFragment fragment = new GameTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_T, translate);
        args.putString(ARG_G, goal);
        args.putString(ARG_D, duration);
        args.putIntegerArrayList(ARG_LIST, themeList);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            translate = getArguments().getString(ARG_T);
            goal = getArguments().getString(ARG_G);
            duration = getArguments().getString(ARG_D);
            themeList = getArguments().getIntegerArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_text_fragment, container, false);

        mTextView = view.findViewById(R.id.seconds_start_tv);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view1 -> {
            Activity activity = getActivity();
            if (activity instanceof GameActivity) {
                activity.onBackPressed();
            }
        });

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
                    ((GameActivity)activity).onGameStart(translate, goal, duration, themeList);
                }
            }
        }.start();
        return view;
    }
}