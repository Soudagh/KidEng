package com.example.kideng.supporting;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static App instance;

    private ThemeDatabase mDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mDataBase = Room.databaseBuilder(this, ThemeDatabase.class, "themesList")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() { return instance; }

    public ThemeDatabase getDatabase() { return mDataBase; }

}
