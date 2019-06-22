package com.example.usersviewgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.usersviewgithub.adapters.UserAdapter;
import com.example.usersviewgithub.api.GitHubApplication;
import com.example.usersviewgithub.api.GitHubService;
import com.example.usersviewgithub.pojo.User;


import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity implements MainPresenter.Listener {

    private RecyclerView recyclerViewUsers;
    private UserAdapter adapter;
    private MainPresenter presenter;
    private Button buttonRetry;
    private ProgressBar loadingBar;

    private int userId = 0;
    private static final String TOKEN = "03680798dc0e63376006827923eb3f7d9cc75798";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        buttonRetry = findViewById(R.id.buttonRetry);
        loadingBar = findViewById(R.id.loadingBar);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        GitHubService service = ((GitHubApplication) getApplication()).getGitHubService();
        presenter = new MainPresenter(service, this);
        presenter.loadUsers(userId, TOKEN);
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        loadingBar.setVisibility(View.GONE);

        adapter = new UserAdapter(users);
        recyclerViewUsers.setAdapter(adapter);
        adapter.setOnUserClickListener(position -> {
            User user = adapter.getUsers().get(position);
            Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
            intent.putExtra("login", user.getLogin());
            startActivity(intent);
        });
        adapter.setOnReachEndListener(position -> {
            loadingBar.setVisibility(View.VISIBLE);
            userId = users.get(position).getId();
            presenter.loadUsers(userId, TOKEN);
        });
    }

    @Override
    public void onError(String message) {
        loadingBar.setVisibility(View.GONE);
        buttonRetry.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stopLoading();
    }

    public void onClickRetry(View view) {
        presenter.loadUsers(userId, TOKEN);
        buttonRetry.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
    }
}
