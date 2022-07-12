package com.example.kideng.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kideng.db.entities.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word WHERE idTheme = :id")
    List<Word> getByThemeId(int id);

    @Query("SELECT * FROM word ORDER BY RANDOM() LIMIT 1")
    Word getRandWord();

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);
}
