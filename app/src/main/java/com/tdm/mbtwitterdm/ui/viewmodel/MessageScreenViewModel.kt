package com.tdm.mbtwitterdm.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tdm.mbtwitterdm.TwitterApplication
import com.tdm.mbtwitterdm.data.*
import com.tdm.mbtwitterdm.data.Target
import com.tdm.mbtwitterdm.data.response.Event
import com.tdm.mbtwitterdm.data.response.EventsList
import com.tdm.mbtwitterdm.data.response.SentMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageScreenViewModel(val userId: String) {

    private val eventList = MutableLiveData<List<Event>>()
    private val sentEvent = MutableLiveData<Event>()
    private val error = MutableLiveData<CharSequence>()

    val events: LiveData<List<Event>>?
        get() = eventList

    val errors: LiveData<CharSequence>
        get() = error

    val sentMsgEvent: LiveData<Event>?
        get() = sentEvent

    fun getMessageList() {
        val msgListCall = TwitterApplication.getInstance().apiClient.customService.getMessageList()
        msgListCall.enqueue(object : Callback<EventsList> {
            override fun onResponse(call: Call<EventsList>, response: Response<EventsList>) {
                when {
                    response?.code() in 200..299 -> {
                        eventList.value = response.body()?.events?.reversed()
                    }
                    else -> error.value = "Unable to get Messages"
                }
            }

            override fun onFailure(call: Call<EventsList>, t: Throwable) {
                error.value = "Unable to get Messages"
            }
        })
    }

    fun sendMessageEvent(usrId: String, msgText: String) {
        val targetId = Target(usrId)
        val text = MessageData(msgText)
        val msgCreate = MessageCreate(targetId, text)
        val msgEvent = MessageEvent(msgCreate = msgCreate)
        val newMsg = NewMessageEvent(msgEvent)

        if (msgText.isEmpty()) {
            error.value = "Can't send empty message"
        } else {
            val sendNewMsg = TwitterApplication.getInstance().apiClient.customService.sendMessage(newMsg)
            sendNewMsg.enqueue(object : Callback<SentMessage> {
                override fun onResponse(call: Call<SentMessage>?, response: Response<SentMessage>?) {
                    when {
                        response?.code() in 200..299 -> {
                            sentEvent.value = response?.body()?.event
                        }
                        else -> error.value = "Unable to Send Message"
                    }
                }

                override fun onFailure(call: Call<SentMessage>?, t: Throwable?) {
                    error.value = "Unable to Send Message"
                }
            })
        }
    }

    /*
    * This function needs to observe the changes happening with the notification, if same user/ in same user with whom
    * messaging is going on it will update the list
    * Else shows a snackbar with new message
    * */
    fun notificationUpdate(event: Event) {
        when {
            event.message_create?.sender_id.equals(userId) -> sentEvent.value = event
            event.message_create?.sender_id.equals(TwitterApplication.getInstance().userInfo.usrId.toString()) -> sentEvent.value = event
            !event.message_create?.sender_id.equals(userId) -> error.value = "New Message"
        }
    }

}
