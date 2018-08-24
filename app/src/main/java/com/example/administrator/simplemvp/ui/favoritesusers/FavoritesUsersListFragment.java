package com.example.administrator.simplemvp.ui.favoritesusers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.administrator.simplemvp.mvp.favoritesusers.FavoritesUsersListPresenter;
import com.example.administrator.simplemvp.mvp.favoritesusers.FavoritesUsersListView;
import com.example.administrator.simplemvp.ui.infousers.InfoUserActivity;

import java.util.List;

public class FavoritesUsersListFragment extends Fragment implements FavoritesUsersListView {

    private RecyclerView favoritesUsersRecyclerView;
    private FavoriteUserItemAdapter favoriteUserItemAdapter;

    private FavoritesUsersListPresenter presenter;
    private static final int REQUEST_CODE_USER_INFO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        favoritesUsersRecyclerView = view.findViewById(R.id.RecyclerView_fragmentFavoritesUsers_user);
        favoritesUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new FavoritesUsersListPresenter(App.getDataManager());
        presenter.attachView(this);
        presenter.getFavoritesUsers();
    }

    @Override
    public void showFavoritesUsers(List<User> favoritesUsers) {
        favoriteUserItemAdapter = new FavoriteUserItemAdapter(favoritesUsers);
        favoriteUserItemAdapter.setOnFavoritesUsersItemListener(favoriteUser -> {
            presenter.itemClick(favoriteUser);
        });
        favoriteUserItemAdapter.setOnRemoveItemListener(favoriteUser -> {
            presenter.deleteUser(favoriteUser);
        });
        favoritesUsersRecyclerView.setAdapter(favoriteUserItemAdapter);
    }

    @Override
    public void startInfoActivity(User user) {
        startActivityForResult(InfoUserActivity.getStartIntent(getContext(), user), REQUEST_CODE_USER_INFO);
    }

    @Override
    public void showProgress(boolean show) {
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}