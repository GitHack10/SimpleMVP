package com.example.administrator.simplemvp.ui.infousers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;

public class InfoUserActivity extends AppCompatActivity {

    private final static String EXTRA_USER = "INFO_USER";

    public static Intent getStartIntent(Context context, User user) {
        Intent intent = new Intent(context, InfoUserActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        user = getIntent().getParcelableExtra(EXTRA_USER);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_main));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        showInfoUserFragment(user);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
        }
        return true;
    }


    private void showInfoUserFragment(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USER, user);
        InfoUserFragment infoUserFragment = new InfoUserFragment();
        infoUserFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_infoUser_container, infoUserFragment).commit();
    }
}