package com.example.administrator.simplemvp.mvp.favoritesusers;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpPresenter;

public class FavoritesUsersListPresenter extends MvpPresenter<FavoritesUsersListView> {
    private DataManager dataManager;

    public FavoritesUsersListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getFavoritesUsers() {
        if (getView() != null) getView().showProgress(true);
        getView().showProgress(false);
        getView().showFavoritesUsers(dataManager.getUsers());
    }

    public void deleteUser(User favoriteUser) {
        dataManager.deleteUser(favoriteUser);
        getView().showFavoritesUsers(dataManager.getUsers());
    }

    public void itemClick(User user) {
        if (getView() != null) getView().startInfoActivity(user);
    }
}