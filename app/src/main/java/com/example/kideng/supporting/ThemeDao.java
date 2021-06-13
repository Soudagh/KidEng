package com.example.kideng.supporting;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.kideng.entities.Theme;

import java.util.List;

@Dao
public interface ThemeDao {

    @Query("SELECT * FROM THEMES_LIST")
    List<Theme> getAll();

}
