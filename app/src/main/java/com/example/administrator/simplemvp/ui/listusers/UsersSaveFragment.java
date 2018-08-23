package com.example.administrator.simplemvp.ui.listusers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.administrator.simplemvp.data.models.User;

import java.util.List;

public class UsersSaveFragment extends Fragment {

    private UserItemAdapter userItemAdapter;

    public UserItemAdapter getUserItemAdapter() {
        return userItemAdapter;
    }

    public void setUserItemAdapter(UserItemAdapter userItemAdapter) {
        this.userItemAdapter = userItemAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}