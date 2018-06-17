package com.tdm.mbtwitterdm.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewMessageEvent(
        @SerializedName("event")
        @Expose var event: MessageEvent) {

}

data class MessageEvent(
        @SerializedName("type")
        @Expose var type: String = "message_create",
        @SerializedName("message_create")
        @Expose var msgCreate: MessageCreate)

data class MessageCreate(
        @SerializedName("target")
        @Expose var target: Target,
        @SerializedName("message_data")
        @Expose var messageData: MessageData)

data class MessageData(
        @SerializedName("text")
        @Expose var text: String)

data class Target(
        @SerializedName("recipient_id")
        @Expose var recipientId: String)