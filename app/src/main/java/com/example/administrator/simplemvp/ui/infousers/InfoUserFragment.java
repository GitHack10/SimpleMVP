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
import android.widget.TextView;

import com.example.administrator.simplemvp.App;
import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class InfoUserFragment extends Fragment {

    private ImageView avatarImageView;
    private User user;
    private Integer idUser;

    private TextView loginTextView;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView publicRepos;
    private TextView followersTextView;
    private TextView followingTextView;

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

        App.getDataManager().getUser(user.getLogin()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void inItViews(@NonNull View view) {
        avatarImageView = view.findViewById(R.id.ImageView_user_info_avatar);

        loginTextView = view.findViewById(R.id.TextView_user_info_login);
        nameTextView = view.findViewById(R.id.TextView_user_info_name);
        locationTextView = view.findViewById(R.id.TextView_user_info_location);
        publicRepos = view.findViewById(R.id.TextView_user_info_public_repos);
        followersTextView = view.findViewById(R.id.TextView_user_info_followers);
        followingTextView = view.findViewById(R.id.TextView_user_info_following);
    }

    private void setData() {
        Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
        loginTextView.setText(user.getLogin());
        nameTextView.setText(user.getName());
        locationTextView.setText(user.getLocation());
        publicRepos.setText(user.getPublicRepos());
        followersTextView.setText(user.getFollowers());
        followingTextView.setText(user.getFollowing());
    }
}