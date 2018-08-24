package com.example.administrator.simplemvp.data.global;

import android.os.AsyncTask;

import com.example.administrator.simplemvp.data.database.AppDatabase;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.data.networks.GithubService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

public class DataManager {

    private GithubService githubService;
    private AppDatabase appDatabase;

    public DataManager(GithubService githubService, AppDatabase appDatabase) {
        this.githubService = githubService;
        this.appDatabase = appDatabase;
    }

    public Call<List<User>> getAllUsers() {
        return githubService.getUsers();
    }

    public Call<User> getUser(String login) {
        return githubService.getUser(login);
    }

    public List<User> getUsers(){
        try {
            return new GetUsers().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Integer> getIdFavoritesUsers() {
        try {
            return new GetIdFavoritesUsers().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertUser(User user) {
        new InsertUser(user).execute();
    }

    public void deleteUser(User user) {
        new DeleteUser(user).execute();
    }

    class GetUsers extends AsyncTask<Void, Void, List<User>>{

        @Override
        protected List<User> doInBackground(Void... voids) {
            return appDatabase.userDao().getUsers();
        }
    }

    class GetIdFavoritesUsers extends AsyncTask<Void, Void, List<Integer>> {

        @Override
        protected List<Integer> doInBackground(Void... voids) {
            List<User> favoritesUsers = appDatabase.userDao().getUsers();
            List<Integer> idFavoriteUser = new ArrayList<>();

            for (User favoriteUser : favoritesUsers) idFavoriteUser.add(favoriteUser.getId());
            return idFavoriteUser;
        }
    }

    class InsertUser extends AsyncTask<Void, Void, Void> {

        private User favoriteUser;

        InsertUser(User favoriteUser) {
            this.favoriteUser = favoriteUser;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.userDao().insert(favoriteUser);
            return null;
        }
    }

    class DeleteUser extends AsyncTask<Void, Void, Void> {

        private User user;

        DeleteUser(User user) {
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.userDao().delete(user);
            return null;
        }
    }
}