package com.example.kideng.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kideng.R;
import com.example.kideng.ui.fragments.dict.AddWordFragment;

public class DictWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        setContentView(R.layout.blank_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_start, AddWordFragment.newInstance(id))
                    .commit();
        }
    }

    public void onBack(int id) {
        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
