package com.example.administrator.simplemvp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.simplemvp.data.models.User;

@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}