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
import android.widget.Toast;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.listusers.MainPresenter;
import com.example.administrator.simplemvp.mvp.listusers.MainView;
import com.example.administrator.simplemvp.ui.infousers.InfoUserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment implements MainView {
    private MainPresenter presenter;
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
        presenter = new MainPresenter(App.getDataManager());
        presenter.attachView(this);
        if (savedInstanceState == null) {
            presenter.getAllUsers();
        }
        else {
            UsersSaveFragment usersSaveFragment = (UsersSaveFragment) getActivity().getSupportFragmentManager().findFragmentByTag(MainActivity.TAG2);
            userItemAdapter = usersSaveFragment.getUserItemAdapter();
            userRecyclerView.setAdapter(userItemAdapter);
        }
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("abc", "cba");
        ((UsersSaveFragment) getActivity().getSupportFragmentManager().findFragmentByTag(MainActivity.TAG2)).setUserItemAdapter(userItemAdapter);
    }

    @Override
    public void showUsersList(List<User> users) {
        userItemAdapter = new UserItemAdapter(users, presenter.getIdFavoritesUsers());
        userItemAdapter.setOnUsersItemListener(user -> {
            presenter.itemClick(user);
        });
        userRecyclerView.setAdapter(userItemAdapter);
    }

    @Override
    public void startInfoActivity(User user) {
        startActivityForResult(InfoUserActivity.getStartIntent(getContext(), user), REQUEST_CODE_USER_INFO);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (usersCall != null) {
            usersCall.cancel();
        }
    }
}