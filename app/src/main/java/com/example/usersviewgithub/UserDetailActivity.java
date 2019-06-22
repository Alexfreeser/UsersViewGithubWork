package com.example.usersviewgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usersviewgithub.api.GitHubApplication;
import com.example.usersviewgithub.api.GitHubService;
import com.example.usersviewgithub.pojo.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserDetailActivity extends AppCompatActivity implements DetailPresenter.ListenerInfo {

    private TextView textViewLogin;
    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewBlog;
    private TextView textViewLocation;
    private TextView textViewEmail;
    private TextView textViewBio;
    private TextView textViewRepos;
    private TextView textViewGists;
    private TextView textViewFollowers;
    private TextView textViewFollowing;
    private TextView textViewCreated;
    private TextView textViewUpdated;
    private ImageView imageViewAvatar;

    private String userLogin;

    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        textViewLogin = findViewById(R.id.textViewLogin);
        textViewId = findViewById(R.id.textViewId);
        textViewName = findViewById(R.id.textViewName);
        textViewBlog = findViewById(R.id.textViewBlog);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewBio = findViewById(R.id.textViewBio);
        textViewRepos = findViewById(R.id.textViewPublicRepos);
        textViewGists = findViewById(R.id.textViewPublicGists);
        textViewFollowers = findViewById(R.id.textViewFollowers);
        textViewFollowing = findViewById(R.id.textViewFollowing);
        textViewCreated = findViewById(R.id.textViewCreated);
        textViewUpdated = findViewById(R.id.textViewUpdated);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("login")) {
            userLogin = intent.getStringExtra("login");
        }
        GitHubService service = ((GitHubApplication) getApplication()).getGitHubService();
        detailPresenter = new DetailPresenter(service, this);
        detailPresenter.loadUserInfo(userLogin);
    }

    @Override
    public void onUserInfoLoaded(UserInfo userInfoList) {
        textViewLogin.setText(userInfoList.getLogin());
        textViewId.setText(String.format("%s", userInfoList.getId()));
        textViewName.setText(userInfoList.getName());
        textViewBlog.setText(userInfoList.getBlog());
        textViewLocation.setText(userInfoList.getLocation());
        if (userInfoList.getEmail() != null) {
            textViewEmail.setText(String.format("%s", userInfoList.getEmail()));
        } else {
            textViewEmail.setText(R.string.no_data);
        }
        if (userInfoList.getBio() != null) {
            textViewBio.setText(String.format("%s", userInfoList.getBio()));
        } else {
            textViewBio.setText(R.string.no_data);
        }
        textViewRepos.setText(String.format("%s", userInfoList.getPublicRepos()));
        textViewGists.setText(String.format("%s", userInfoList.getPublicGists()));
        textViewFollowers.setText(String.format("%s", userInfoList.getFollowers()));
        textViewFollowing.setText(String.format("%s", userInfoList.getFollowing()));
        textViewCreated.setText(userInfoList.getCreatedAt());
        textViewUpdated.setText(userInfoList.getUpdatedAt());
        Picasso.get().load(userInfoList.getAvatarUrl()).placeholder(R.drawable.placeholder).into(imageViewAvatar);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.stopLoading();
    }
}
