package com.tdm.mbtwitterdm.ui.viewmodel;

import com.tdm.mbtwitterdm.data.MessageCreate;
import com.tdm.mbtwitterdm.data.MessageData;
import com.tdm.mbtwitterdm.data.MessageEvent;
import com.tdm.mbtwitterdm.data.NewMessageEvent;
import com.tdm.mbtwitterdm.data.Target;

public class MessageScreenViewModel {


    public void sendMessage(String userId, String msg) {
        Target targetId = new Target(userId);
        MessageData text = new MessageData(msg);
        MessageCreate msgCreate = new MessageCreate(targetId, text);
        MessageEvent msgEvent = new MessageEvent("message_create", msgCreate);
        NewMessageEvent newMsg = new NewMessageEvent(msgEvent);


    }
}
