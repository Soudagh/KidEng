package com.example.kideng.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "WORDS_LIST")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int wordId;
    @ColumnInfo(name = "id_theme")
    public int themeId;
    @ColumnInfo(name = "ru")
    public @NonNull
    String wordRus;
    @ColumnInfo(name = "eng")
    public @NonNull
    String wordEng;


    public Word(@NonNull int wordId, @NonNull String wordRus, @NonNull String wordEng) {
        this.wordId = wordId;
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }


//    public int getWordId() { return wordId; }
//
//    public int getThemeId() { return themeId; }

//    public String getWordRus() {
//        return wordRus;
//    }
//
//    public String getWordEng() {
//        return wordEng;
//    }

}
