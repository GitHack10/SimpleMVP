package com.example.administrator.simplemvp.mvp.global;

public class MvpPresenter<Type extends MvpView> {

    private Type view;

    public void attachView(Type view) {
        this.view = view;
    }

    public void detechView() {
        view = null;
    }

    protected Type getView() {
        return view;
    }
}