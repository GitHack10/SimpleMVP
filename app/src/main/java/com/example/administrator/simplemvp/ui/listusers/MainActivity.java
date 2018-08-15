package com.example.administrator.simplemvp.ui.listusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.simplemvp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    UsersListFragment usersListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersListFragment = (UsersListFragment) getSupportFragmentManager().findFragmentByTag(TAG);

        if (usersListFragment == null) {
            usersListFragment = new UsersListFragment();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.FrameLayout_main_container, usersListFragment, TAG).commit();
        }
    }
}