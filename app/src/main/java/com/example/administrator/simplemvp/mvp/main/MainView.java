package com.example.administrator.simplemvp.mvp.main;

import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpView;

import java.util.List;

public interface MainView extends MvpView {

    void showListUsers(List<User> users);
}