package com.example.kideng.ui.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kideng.R;

public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {}

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
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
        View view =  inflater.inflate(R.layout.fragment_about_us, container, false);
        TextView mTgLink = view.findViewById(R.id.tg_link);
        TextView mDsLink = view.findViewById(R.id.ds_link);
        TextView mGhLink = view.findViewById(R.id.gh_link);

        if (mTgLink != null) {
            mTgLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        if (mDsLink != null) {
            mDsLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        if (mGhLink != null) {
            mGhLink.setMovementMethod(LinkMovementMethod.getInstance());
        }

        return view;
    }
}