package com.example.administrator.simplemvp.ui.listusers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.listusers.UsersListPresenter;
import com.example.administrator.simplemvp.mvp.listusers.UsersListView;
import com.example.administrator.simplemvp.ui.infousers.InfoUserActivity;

import java.util.List;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment implements UsersListView {
    private UsersListPresenter presenter;
    private RecyclerView userRecyclerView;
    private UserItemAdapter userItemAdapter;
    private ProgressBar progressBar;
    private Call<List<User>> usersCall;

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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(userRecyclerView.getContext(),((LinearLayoutManager)userRecyclerView.getLayoutManager()).getOrientation());
        userRecyclerView.addItemDecoration(dividerItemDecoration);
        progressBar = view.findViewById(R.id.progress_main);
        presenter = new UsersListPresenter(App.getDataManager());
        presenter.attachView(this);
        presenter.getAllUsers();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsersList(List<User> users, List<Integer> idFavoritesUsers) {
        userItemAdapter = new UserItemAdapter(users, idFavoritesUsers);
        userItemAdapter.setOnUsersItemListener(user -> {
            presenter.itemClick(user);
        });
        userItemAdapter.setAddUserIconClickListener(user -> {
            presenter.insertUser(user);
        });
        userRecyclerView.setAdapter(userItemAdapter);
    }

    @Override
    public void startInfoActivity(User user) {
        startActivityForResult(InfoUserActivity.getStartIntent(getContext(), user), REQUEST_CODE_USER_INFO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == MainActivity.REQUEST_CODE_FAVORITES) {
            userItemAdapter.setIdFavoritesUsers(presenter.getIdFavoritesUsers());
            userItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (usersCall != null) {
            usersCall.cancel();
        }
    }
}