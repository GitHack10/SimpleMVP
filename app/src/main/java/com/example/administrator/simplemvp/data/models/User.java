package com.example.administrator.simplemvp.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity()
public class User implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("location")
    private String location;
    @SerializedName("name")
    private String name;
    @SerializedName("public_repos")
    private String publicRepos;
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;

    public User(int id, String login, String avatarUrl, String location, String name, String publicRepos, String followers, String following) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.location = location;
        this.name = name;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
    }

    protected User(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatarUrl = in.readString();
        location = in.readString();
        name = in.readString();
        publicRepos = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
        parcel.writeString(location);
        parcel.writeString(name);
        parcel.writeString(publicRepos);
        parcel.writeString(followers);
        parcel.writeString(following);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }
}