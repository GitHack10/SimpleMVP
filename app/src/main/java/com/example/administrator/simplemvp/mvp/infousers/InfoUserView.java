package com.example.administrator.simplemvp.mvp.infousers;

import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.global.MvpView;

public interface InfoUserView extends MvpView {

    void showInfoUser(User user);
}