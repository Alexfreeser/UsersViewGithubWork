package com.example.usersviewgithub;

import com.example.usersviewgithub.api.GitHubService;
import com.example.usersviewgithub.pojo.User;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    public interface Listener {

        void onUsersLoaded(List<User> users);

        void onError(String message);
    }

    private GitHubService gitHubService;
    private Listener listener;
    private Disposable disposable;

    public MainPresenter(GitHubService apiService, Listener listener) {
        this.gitHubService = apiService;
        this.listener = listener;
    }

    public void loadUsers(int userId, String token) {
        disposable = gitHubService.listUser(userId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> listener.onUsersLoaded(users), error -> listener.onError(error.getMessage()));
    }

    public void stopLoading() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
