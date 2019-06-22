package com.example.usersviewgithub;

import com.example.usersviewgithub.api.GitHubService;
import com.example.usersviewgithub.pojo.User;
import com.example.usersviewgithub.pojo.UserInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter {

    public interface ListenerInfo {

        void onUserInfoLoaded(UserInfo userInfoList);

        void onError(String message);
    }

    private GitHubService gitHubService;
    private ListenerInfo listener;
    private Disposable disposable;

    public DetailPresenter(GitHubService gitHubService, ListenerInfo listener) {
        this.gitHubService = gitHubService;
        this.listener = listener;
    }

    public void loadUserInfo(String userLogin) {
        disposable = gitHubService.getUserInfo(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfoList -> listener.onUserInfoLoaded(userInfoList), error -> listener.onError(error.getMessage()));
    }

    public void stopLoading() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
