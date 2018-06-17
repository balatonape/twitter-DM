package com.tdm.mbtwitterdm.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdm.mbtwitterdm.databinding.FriendsListItemViewBinding;
import com.tdm.mbtwitterdm.ui.MessageScreen;
import com.tdm.mbtwitterdm.ui.viewmodel.FollowerItemViewModel;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.BindingHolder> {

    private Context mContext;
    private List<User> friendsList;
    private View emptyView;

    public FollowerAdapter(Context context, List<User> friendsList, View emptyView) {
        mContext = context;
        this.friendsList = new ArrayList<>(friendsList);
        this.emptyView = emptyView;
        this.emptyView.setVisibility(friendsList.size() == 0 ? View.VISIBLE : View.GONE);
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FriendsListItemViewBinding binding = FriendsListItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(FollowerAdapter.BindingHolder holder, final int position) {
        User user = friendsList.get(position);
        ((FriendsListItemViewBinding) holder.getBinding()).setUser(new FollowerItemViewModel(user));

        holder.getBinding().getRoot().setOnClickListener(v -> {
            Intent msgDetail = new Intent(mContext, MessageScreen.class);
            msgDetail.putExtra("usrName", user.name + " @" + user.screenName);
            msgDetail.putExtra("usrId", user.id + "");
            msgDetail.putExtra("avatarUrl", user.profileImageUrl);
            mContext.startActivity(msgDetail);
        });
    }

    @Override
    public int getItemCount() {
        return friendsList != null ? friendsList.size() : 0;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private FriendsListItemViewBinding binding;

        BindingHolder(FriendsListItemViewBinding rowView) {
            super(rowView.getRoot());
            binding = rowView;
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }
}
