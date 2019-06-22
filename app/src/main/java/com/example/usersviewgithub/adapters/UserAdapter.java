package com.example.usersviewgithub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usersviewgithub.R;
import com.example.usersviewgithub.pojo.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;

    private OnUserClickListener onUserClickListener;
    private OnReachEndListener onReachEndListener;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    public interface OnUserClickListener {
        void onUserClick(int position);
    }

    public interface OnReachEndListener{
        void onReachEnd(int position);
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        if (i > users.size() - 2 && onReachEndListener != null) {
            onReachEndListener.onReachEnd(i);
        }
        User user = users.get(i);
        userViewHolder.textViewUserName.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUserClickListener != null) {
                        onUserClickListener.onUserClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
