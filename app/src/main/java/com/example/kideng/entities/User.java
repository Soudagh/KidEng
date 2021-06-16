package com.example.kideng.entities;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String nickname;
    private String name;


    public User(int id, String nickname, String age) {
        this.id = id;
        this.nickname = nickname;
        this.name = age;

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


}
