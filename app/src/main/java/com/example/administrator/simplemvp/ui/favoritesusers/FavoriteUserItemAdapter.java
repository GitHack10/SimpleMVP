package com.example.administrator.simplemvp.ui.favoritesusers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.simplemvp.R;
import com.example.administrator.simplemvp.data.models.User;
import com.example.administrator.simplemvp.ui.listusers.UserItemAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteUserItemAdapter extends RecyclerView.Adapter<FavoriteUserItemAdapter.FavoritesUsersItemViewHolder> {

    private List<User> favoritesUsers;

    private OnRemoveItemListener onRemoveItemListener;
    private OnFavoritesUsersItemListener onFavoritesUsersItemListener;

    public FavoriteUserItemAdapter(List<User> favoritesUsers) {
        this.favoritesUsers = favoritesUsers;
    }

    @NonNull
    @Override
    public FavoritesUsersItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_favorite_user, parent, false);
        return new FavoritesUsersItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesUsersItemViewHolder holder, int position) {
        holder.setFavoritesUsersData(favoritesUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritesUsers.size();
    }

    public void setOnRemoveItemListener(OnRemoveItemListener onRemoveItemListener) {
        this.onRemoveItemListener = onRemoveItemListener;
    }

    public void setOnFavoritesUsersItemListener(OnFavoritesUsersItemListener onFavoritesUsersItemListener) {
        this.onFavoritesUsersItemListener = onFavoritesUsersItemListener;
    }

    public interface OnFavoritesUsersItemListener {
        void onFavoriteUserItemClick(User user);
    }

    public interface OnRemoveItemListener {
        void onRemoveItemClick(User favoriteUser);
    }

    class FavoritesUsersItemViewHolder extends RecyclerView.ViewHolder {

        private TextView loginTextView;
        private TextView idTextView;
        private CircleImageView avatarCircleImageView;
        private ImageView removeImageView;

        FavoritesUsersItemViewHolder(View itemView) {
            super(itemView);

            loginTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userName);
            idTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userId1);
            avatarCircleImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_icon);
            removeImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_removeUser);
        }

        void setFavoritesUsersData(User favoriteUserData) {
            Picasso.get().load(favoriteUserData.getAvatarUrl()).into(avatarCircleImageView);
            loginTextView.setText(favoriteUserData.getLogin());
            idTextView.setText(String.valueOf(favoriteUserData.getId()));
            removeImageView.setOnClickListener(view -> {
                if (onRemoveItemListener != null) onRemoveItemListener.onRemoveItemClick(favoriteUserData);
                Toast.makeText(itemView.getContext(), itemView.getContext().getResources()
                        .getString(R.string.msg_removeUser_in_favorites), Toast.LENGTH_SHORT).show();
            });
            itemView.setOnClickListener(view -> {
                if (onFavoritesUsersItemListener != null) onFavoritesUsersItemListener.onFavoriteUserItemClick(favoriteUserData);
            });
        }
    }
}