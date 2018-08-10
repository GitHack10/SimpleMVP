package com.example.administrator.simplemvp.mvp.main;

import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends MvpPresenter<MainView>{
    private DataManager dataManager;

    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getAllUsers() {

        getView().showProgress(true);
        dataManager.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    getView().showListUsers(users);
                    getView().showProgress(false);
                    getView().showMessage("done");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                getView().showMessage("no Network!");
            }
        });
    }

    public void insertUser(User user) {
        dataManager.insertUser(user);
    }
}