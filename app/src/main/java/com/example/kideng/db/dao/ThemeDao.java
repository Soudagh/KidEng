package com.example.kideng.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.kideng.db.entities.Theme;

import java.util.List;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM theme")
    List<Theme> getAll();

}
