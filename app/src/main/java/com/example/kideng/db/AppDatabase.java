package com.example.kideng.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.kideng.db.dao.ThemeDao;
import com.example.kideng.db.dao.UserDao;
import com.example.kideng.db.dao.WordDao;
import com.example.kideng.db.entities.Theme;
import com.example.kideng.db.entities.User;
import com.example.kideng.db.entities.Word;

@Database(entities = {Theme.class, User.class, Word.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ThemeDao themeDao();
    public abstract WordDao wordDao();
    public abstract UserDao userDao();

//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE theme ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
//        }
//    };



}
