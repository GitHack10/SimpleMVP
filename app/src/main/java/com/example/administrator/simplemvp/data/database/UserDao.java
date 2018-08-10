package com.example.administrator.simplemvp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.administrator.simplemvp.data.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User favoriteUser);

    @Delete()
    void delete(User favoriteUser);
}