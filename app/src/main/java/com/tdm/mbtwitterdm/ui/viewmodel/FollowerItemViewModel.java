package com.tdm.mbtwitterdm.ui.viewmodel;

import android.databinding.ObservableField;

import com.twitter.sdk.android.core.models.User;

public class FollowerItemViewModel {
    public final ObservableField<String> avatarUrl = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userDesc = new ObservableField<>();

    public FollowerItemViewModel(User user) {
        avatarUrl.set(user.profileImageUrl);
        userName.set(user.name + " @" + user.screenName);
        userDesc.set(user.description);
    }
}
