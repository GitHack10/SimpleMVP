package com.example.administrator.simplemvp.mvp.infousers;

import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoUserPresenter extends MvpPresenter<InfoUserView> {
    private DataManager dataManager;
    private InfoUserView view;
    private User user;

    public InfoUserPresenter(DataManager dataManager, InfoUserView view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    public void getUser() {
        if (getView() != null) getView().showProgress(true);
        dataManager.getUser(user.getLogin()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
