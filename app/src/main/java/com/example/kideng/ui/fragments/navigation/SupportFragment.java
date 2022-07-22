package com.example.kideng.ui.fragments.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kideng.R;

public class SupportFragment extends Fragment {

    public SupportFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);

        Button button = view.findViewById(R.id.form_bt);
        button.setOnClickListener(this::onClick);

        return view;
    }

    private void onClick(View view) {
        Uri uri = Uri.parse("https://forms.gle/33ApuWHXZxyvqR8M7");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}