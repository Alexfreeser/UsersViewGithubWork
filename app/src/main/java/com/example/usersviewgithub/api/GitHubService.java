package com.example.usersviewgithub.api;

import com.example.usersviewgithub.pojo.User;
import com.example.usersviewgithub.pojo.UserInfo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("users")
    Single<List<User>> listUser(@Query("since") int userId);

    @GET("/users/{username}")
    Single<UserInfo> getUserInfo(@Path("username") String username);
}
