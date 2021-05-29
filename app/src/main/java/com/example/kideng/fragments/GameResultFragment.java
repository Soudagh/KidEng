package com.example.kideng.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.R;
import com.example.kideng.activities.GameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class GameResultFragment extends Fragment {

    private static final String ARG_Int1 = "1";
    private static final String ARG_Int2 = "2";
    private static final String ARG_Int3 = "3";

    private String mRCount;
    private String mWCount;
    private String mTCount;

    public GameResultFragment() {

    }

    public static GameResultFragment newInstance(String rCounter, String wCounter, String tCounter) {
        GameResultFragment fragment = new GameResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Int1, rCounter);
        args.putString(ARG_Int2, wCounter);
        args.putString(ARG_Int3, tCounter);
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