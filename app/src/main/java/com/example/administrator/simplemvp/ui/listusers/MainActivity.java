package com.example.administrator.simplemvp.ui.listusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.simplemvp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    public static final String TAG2 = "tag2";
    public static final int REQUEST_CODE_FAVORITES = 2;

    UsersListFragment usersListFragment;
    UsersSaveFragment usersSaveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        usersListFragment = (UsersListFragment) getSupportFragmentManager().findFragmentByTag(TAG);
        usersSaveFragment = (UsersSaveFragment) getSupportFragmentManager().findFragmentByTag(TAG2);

        if (usersListFragment == null) {
            usersListFragment = new UsersListFragment();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.FrameLayout_main_container, usersListFragment, TAG).commit();
        }

        if (usersSaveFragment == null) {
            usersSaveFragment = new UsersSaveFragment();
            getSupportFragmentManager().beginTransaction().add(usersSaveFragment, TAG2).commit();
        }
    }
}