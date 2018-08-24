package com.example.administrator.simplemvp.mvp.favoritesusers;

import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpView;

import java.util.List;

public interface FavoritesUsersListView extends MvpView {

    void showFavoritesUsers(List<User> favoritesUsers);

    void startInfoActivity(User user);
}