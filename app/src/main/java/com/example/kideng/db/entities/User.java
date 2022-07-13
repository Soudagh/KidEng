package com.example.kideng.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nickname;
    private String name;

    public User(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
