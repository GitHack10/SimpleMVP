package com.example.administrator.simplemvp.ui.listusers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.listusers.MainPresenter;
import com.example.administrator.simplemvp.mvp.listusers.MainView;

import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment implements MainView {
    private MainPresenter presenter;
    private List<User> users;
    private RecyclerView userRecyclerView;
    private UserItemAdapter userItemAdapter;
    private Call<List<User>> usersCall;

    private final static String EXTRA_USER = "INFO_USER";
    private static final int REQUEST_CODE_USER_INFO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userRecyclerView = view.findViewById(R.id.RecyclerView_main_user);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new MainPresenter(App.getDataManager(), this);
        presenter.attachView(this);
        presenter.getAllUsers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (usersCall != null) {
            usersCall.cancel();
        }
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showUsersListFragment(List<User> users) {
        userItemAdapter = new UserItemAdapter(users);
        userRecyclerView.setAdapter(userItemAdapter);
    }
}