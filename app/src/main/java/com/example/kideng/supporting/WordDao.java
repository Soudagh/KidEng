package com.example.kideng.supporting;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.kideng.entities.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM WORDS_LIST WHERE id_theme LIKE :id")
    List<Word> getWords(int id);

    //@Query()

}
