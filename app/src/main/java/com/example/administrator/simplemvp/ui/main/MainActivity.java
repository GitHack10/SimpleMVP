package com.example.administrator.simplemvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.main.MainPresenter;
import com.example.administrator.simplemvp.mvp.main.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(App.getDataManager());
        presenter.attachView(this);
        presenter.getAllUsers();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListUsers(List<User> users) {
        presenter.insertUser(users.get(0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detechView();
    }
}