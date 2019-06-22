package com.example.usersviewgithub.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class User {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;

    public User(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
