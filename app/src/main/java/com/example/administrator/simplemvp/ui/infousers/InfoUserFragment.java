package com.example.administrator.simplemvp.ui.infousers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.mvp.infousers.InfoUserPresenter;
import com.example.administrator.simplemvp.mvp.infousers.InfoUserView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class InfoUserFragment extends Fragment implements InfoUserView {
    private InfoUserPresenter presenter;
    private ImageView avatarImageView;
    private User user;

    private TextView loginTextView;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView publicRepos;
    private TextView followersTextView;
    private TextView followingTextView;

    private TextView defaultLoginTextView;
    private TextView defaultNameTextView;
    private TextView defaultLocationTextView;
    private TextView defaultPublicRepos;
    private TextView defaultFollowersTextView;
    private TextView defaultFollowingTextView;
    private ProgressBar progressBar;

    private final static String EXTRA_USER = "INFO_USER";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inItViews(view);

        if (getArguments() != null) {
            user = getArguments().getParcelable(EXTRA_USER);
        }

        presenter = new InfoUserPresenter(App.getDataManager());
        presenter.attachView(this);
        presenter.getUser(user.getLogin());
    }

    private void inItViews(@NonNull View view) {
        avatarImageView = view.findViewById(R.id.ImageView_user_info_avatar);
        progressBar = view.findViewById(R.id.progress_info);

        loginTextView = view.findViewById(R.id.TextView_user_info_login);
        nameTextView = view.findViewById(R.id.TextView_user_info_name);
        locationTextView = view.findViewById(R.id.TextView_user_info_location);
        publicRepos = view.findViewById(R.id.TextView_user_info_public_repos);
        followersTextView = view.findViewById(R.id.TextView_user_info_followers);
        followingTextView = view.findViewById(R.id.TextView_user_info_following);

        defaultLoginTextView = view.findViewById(R.id.TextView_user_info_defaultLogin);
        defaultNameTextView = view.findViewById(R.id.TextView_user_info_defaultName);
        defaultLocationTextView = view.findViewById(R.id.TextView_user_info_defaultLocation);
        defaultPublicRepos = view.findViewById(R.id.TextView_user_info_public_defaultRepos);
        defaultFollowersTextView = view.findViewById(R.id.TextView_user_info_defaultFollowers);
        defaultFollowingTextView = view.findViewById(R.id.TextView_user_info_defaultFollowing);
    }

    private void setData(User userData) {
        Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);

        defaultLoginTextView.setVisibility(View.VISIBLE);
        defaultNameTextView.setVisibility(View.VISIBLE);
        defaultLocationTextView.setVisibility(View.VISIBLE);
        defaultPublicRepos.setVisibility(View.VISIBLE);
        defaultFollowersTextView.setVisibility(View.VISIBLE);
        defaultFollowingTextView.setVisibility(View.VISIBLE);

        loginTextView.setText(userData.getLogin());
        nameTextView.setText(userData.getName());
        locationTextView.setText(userData.getLocation());
        publicRepos.setText(userData.getPublicRepos());
        followersTextView.setText(userData.getFollowers());
        followingTextView.setText(userData.getFollowing());
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showInfoUser(User user) {
        setData(user);
    }
}