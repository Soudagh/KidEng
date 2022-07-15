package com.example.kideng.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private final int idTheme;
    private String wordRus;
    private String wordEng;

    public Word(int idTheme, String wordRus, String wordEng) {
        this.idTheme = idTheme;
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }

    public int getId() {return id;}

    public int getIdTheme() {return idTheme;}

    public String getWordRus() {
        return wordRus;
    }

    public String getWordEng() {
        return wordEng;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWordRus(String wordRus) {
        this.wordRus = wordRus;
    }

    public void setWordEng(String wordEng) {
        this.wordEng = wordEng;
    }
}
