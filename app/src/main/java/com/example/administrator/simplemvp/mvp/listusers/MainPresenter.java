package com.example.administrator.simplemvp.mvp.listusers;

import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends MvpPresenter<MainView> {
    private DataManager dataManager;

    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getAllUsers() {

        if (getView() != null) getView().showProgress(true);
        dataManager.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && getView() != null) {
                    List<User> users = response.body();
                    List<Integer> idFavoritesUsers = getIdFavoritesUsers();
                    getView().showProgress(false);
                    getView().showMessage("done");
                    getView().showUsersList(users, idFavoritesUsers);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (getView() != null) getView().showMessage("no Network!");
            }
        });
    }

    public List<Integer> getIdFavoritesUsers() {
        return dataManager.getIdFavoritesUsers();
    }

    public void insertUser(User user) {
        dataManager.insertUser(user);
    }

    public void itemClick(User user) {
        if (getView() != null) getView().startInfoActivity(user);
    }
}