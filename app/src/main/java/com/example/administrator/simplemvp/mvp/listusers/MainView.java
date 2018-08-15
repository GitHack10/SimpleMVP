package com.example.administrator.simplemvp.mvp.listusers;

import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpView;

import java.util.List;

public interface MainView extends MvpView {

    void showUsersListFragment(List<User> users);
}