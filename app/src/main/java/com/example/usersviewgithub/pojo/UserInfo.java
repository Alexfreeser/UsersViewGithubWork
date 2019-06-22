package com.example.usersviewgithub.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("blog")
    @Expose
    private String blog;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("email")
    @Expose
    private Object email;

    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("public_repos")
    @Expose
    private int publicRepos;
    @SerializedName("public_gists")
    @Expose
    private int publicGists;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("following")
    @Expose
    private int following;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
