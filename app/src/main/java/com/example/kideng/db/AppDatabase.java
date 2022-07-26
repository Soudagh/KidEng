package com.example.kideng.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.dao.UserDao;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.db.entities.User;
import com.example.kideng.db.entities.Word;

@Database(entities = {Theme.class, User.class, Word.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ThemeDao themeDao();
    public abstract WordDao wordDao();
    public abstract UserDao userDao();

}
