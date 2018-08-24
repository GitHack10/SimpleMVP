package com.example.administrator.simplemvp.mvp.infousers;

import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoUserPresenter extends MvpPresenter<InfoUserView> {
    private DataManager dataManager;

    public InfoUserPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getUser(String login) {

        if (getView() != null) getView().showProgress(true);
        dataManager.getUser(login).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    getView().showProgress(false);
                    getView().showInfoUser(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}