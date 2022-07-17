package com.example.kideng.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kideng.db.entities.Theme;

import java.util.List;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM theme")
    List<Theme> getAll();

    @Query("SELECT id FROM theme")
    List<Integer> getAllIds();

    @Query("SELECT theme FROM theme")
    String[] getAllThemes();

    @Query("SELECT * FROM theme WHERE id = :id")
    Theme getThemeById(int id);

    @Insert
    void insert(Theme theme);
}
