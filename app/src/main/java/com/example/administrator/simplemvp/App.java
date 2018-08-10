package com.example.administrator.simplemvp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.administrator.simplemvp.data.database.AppDatabase;
import com.example.administrator.simplemvp.data.global.DataManager;
import com.example.administrator.simplemvp.data.networks.GithubService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://api.github.com/";

    private static DataManager dataManager;


    @Override
    public void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        GithubService githubService = retrofit.create(GithubService.class);

        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();

        DataManager dataManager = new DataManager(githubService, appDatabase);
    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}