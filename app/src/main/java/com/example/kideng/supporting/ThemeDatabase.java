package com.example.kideng.supporting;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kideng.entities.Theme;
import com.example.kideng.entities.Word;

@Database(entities = {Theme.class, Word.class}, version = 10)
public abstract class ThemeDatabase extends RoomDatabase {
    public abstract ThemeDao themeDao();
    public abstract WordDao wordDao();
}
