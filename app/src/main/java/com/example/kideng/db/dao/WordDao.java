package com.example.kideng.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kideng.db.entities.Word;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word WHERE idTheme = :id")
    List<Word> getByThemeId(int id);

    @Query("SELECT * FROM word WHERE wordEng = :word OR wordRus = :word")
    Word getWordByName(String word);

    @Query("SELECT * FROM word WHERE id = :id")
    Word getWordById(int id);

    @Query("SELECT * FROM word WHERE idTheme in (:array) ORDER BY RANDOM() LIMIT 1")
    Word getRandWord(List<Integer> array);

    @Insert
    void insert(Word word);

    @Update
    void update(Word word);

    @Delete
    void delete(Word word);
}
