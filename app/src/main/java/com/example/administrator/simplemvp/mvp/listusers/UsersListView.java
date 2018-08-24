package com.example.administrator.simplemvp.mvp.listusers;

import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpView;

import java.util.List;

public interface UsersListView extends MvpView {

    void showUsersList(List<User> users, List<Integer> idFavoritesUsers);

    void startInfoActivity(User user);
}