package com.example.kideng.ui.fragments.dict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.kideng.App;
import com.example.kideng.R;
import com.example.kideng.db.AppDatabase;
import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.ui.activities.DictThemeActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddThemeFragment extends Fragment {

    private TextInputEditText textInputEditTextThemeNameEt;
    private TextInputEditText textInputEditTextThemeDescEt;
    private TextInputLayout textInputThemeNameLayout;
    private TextInputLayout textInputThemeDescLayout;

    public static AddThemeFragment newInstance() {
        AddThemeFragment fragment = new AddThemeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_theme_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view1 -> {
            Activity activity = getActivity();
            if (activity instanceof DictThemeActivity) {
                ((DictThemeActivity)activity).onBack();
            }
        });

        textInputEditTextThemeNameEt = view.findViewById(R.id.textInputThemeNamedEt);
        textInputEditTextThemeDescEt = view.findViewById(R.id.textInputThemeDescEt);
        textInputThemeNameLayout = view.findViewById(R.id.textInputThemeNameLayout);
        textInputThemeDescLayout = view.findViewById(R.id.textInputThemeDescLayout);

        Button button = view.findViewById(R.id.theme_bt);
        button.setOnClickListener(this::addTheme);
    }

    public void addTheme(View view) {
        if (String.valueOf(textInputEditTextThemeNameEt.getText()).equals("") || String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
            if (String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
                textInputThemeNameLayout.setErrorEnabled(true);
                textInputThemeNameLayout.setError(getString(R.string.requiredField));
            }
            if (String.valueOf(textInputEditTextThemeDescEt.getText()).equals("")) {
                textInputThemeDescLayout.setErrorEnabled(true);
                textInputThemeDescLayout.setError(getString(R.string.requiredField));
            }
        } else {
            AppDatabase db = App.getInstance().getDatabase();
            ThemeDao themeDao = db.themeDao();
            Theme theme = new Theme(String.valueOf(textInputEditTextThemeNameEt.getText()),
                    String.valueOf(textInputEditTextThemeDescEt.getText()));
            themeDao.insert(theme);
            Activity activity = getActivity();
            if (activity instanceof DictThemeActivity) {
                ((DictThemeActivity)activity).onBack();
            }
        }
    }
}
