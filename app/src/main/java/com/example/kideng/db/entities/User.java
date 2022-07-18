package com.example.kideng.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int totalTrains;
    private int totalWords;
    private int rightAnswers;
    private int wrongAnswers;

    public User(int totalTrains, int totalWords, int rightAnswers, int wrongAnswers) {
        this.totalTrains = totalTrains;

        this.totalWords = totalWords;
        this.rightAnswers = rightAnswers;
        this.wrongAnswers = wrongAnswers;
    }
    public int getId() {
        return id;
    }

    public int getTotalTrains() {
        return totalTrains;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalTrains(int totalTrains) {
        this.totalTrains = totalTrains;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
