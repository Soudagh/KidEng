package com.example.kideng.ui.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.UserDao;
import com.example.kideng.db.entities.User;


public class StatisticsFragment extends Fragment {

    public StatisticsFragment() {
    }


    public static StatisticsFragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        AppDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();
        User user = userDao.getUser();

        TextView mTotalGames = view.findViewById(R.id.total_games_tv);
        TextView mTotalAnswers = view.findViewById(R.id.total_answers_tv);
        TextView mRightAnswers = view.findViewById(R.id.right_answers_tv);
        TextView mWrongAnswers = view.findViewById(R.id.wrong_answers_tv);

        mTotalGames.setText(String.valueOf(user.getTotalTrains()));
        mTotalAnswers.setText(String.valueOf(user.getTotalWords()));
        mRightAnswers.setText(String.valueOf(user.getRightAnswers()));
        mWrongAnswers.setText(String.valueOf(user.getWrongAnswers()));
        return view;
    }
}