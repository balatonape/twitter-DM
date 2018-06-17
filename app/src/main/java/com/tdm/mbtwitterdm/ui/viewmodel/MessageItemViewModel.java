package com.tdm.mbtwitterdm.ui.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.tdm.mbtwitterdm.TwitterApplication;
import com.tdm.mbtwitterdm.data.response.Event;

public class MessageItemViewModel {
    public final ObservableField<String> avatarUrl = new ObservableField<>();
    public final ObservableField<String> msg = new ObservableField<>();
    public final ObservableBoolean isSelf = new ObservableBoolean();

    public MessageItemViewModel(Event event, String url) {

        msg.set(event.getMessage_create().getMessage_data().getText());
        String userId = TwitterApplication.getInstance().getUserInfo().getUsrId() + "";

        if (userId.equals(event.getMessage_create().getSender_id())) isSelf.set(true);
        else isSelf.set(false);

        if (!isSelf.get()) {
            avatarUrl.set(url);
        } else {
            avatarUrl.set("");
        }
    }

}
