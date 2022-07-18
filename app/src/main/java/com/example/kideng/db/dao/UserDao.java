package com.example.kideng.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kideng.db.entities.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    User getUser();

    @Insert
    void insert(User user);

    @Update
    void update(User user);
}
