package com.tdm.mbtwitterdm.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tdm.mbtwitterdm.R
import com.tdm.mbtwitterdm.TwitterApplication
import com.tdm.mbtwitterdm.data.*
import com.tdm.mbtwitterdm.data.Target
import com.tdm.mbtwitterdm.ui.adapters.MessagesAdapter
import kotlinx.android.synthetic.main.message_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageScreen : AppCompatActivity() {
    lateinit var avatarUrl: String
    lateinit var adapter: MessagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_screen)

        val title: String = intent.getStringExtra("usrName")
        val usrId: String = intent.getStringExtra("usrId")
        avatarUrl = intent.getStringExtra("avatarUrl")
        msg_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        setTitle(title)

        val msgListCall = TwitterApplication.getInstance().apiClient.customService.getMessageList()
        msgListCall.enqueue(object : Callback<EventsList> {
            override fun onResponse(call: Call<EventsList>?, response: Response<EventsList>?) {
                when {
                    response?.code() in 200..299 -> {
                        adapter = MessagesAdapter(response?.body()?.events?.reversed(), emptyView, avatarUrl)
                        msg_list.adapter = adapter
                        msg_list.smoothScrollToPosition(adapter.itemCount - 1)
                    }
                    else -> {
                        showSnackBar("Failed to get Messages")
                    }
                }
            }

            override fun onFailure(call: Call<EventsList>?, t: Throwable?) {
                showSnackBar("Failed to get Messages")
            }
        })

        send_msg.setOnClickListener {
            val targetId = Target(usrId)
            val text = MessageData(msg_text.text.toString())
            val msgCreate = MessageCreate(targetId, text)
            val msgEvent = MessageEvent(msgCreate = msgCreate)
            val newMsg = NewMessageEvent(msgEvent)

            val sendNewMsg = TwitterApplication.getInstance().apiClient.customService.sendMessage(newMsg)
            sendNewMsg.enqueue(object : Callback<SentMessage> {
                override fun onResponse(call: Call<SentMessage>?, response: Response<SentMessage>?) {
                    when {
                        response?.code() in 200..299 -> {
                            msg_text.setText("")
                            adapter.updateEventList(response?.body()?.event)
                            msg_list.smoothScrollToPosition(adapter.itemCount - 1)
                        }
                        else -> showSnackBar("Failed to send Message")
                    }
                }

                override fun onFailure(call: Call<SentMessage>?, t: Throwable?) {
                    showSnackBar("Failed to send Message")
                }
            })
        }
    }

    fun showSnackBar(string: String) {
        Snackbar.make(parentLayout, string, Snackbar.LENGTH_LONG).show()
    }

    fun updateMessageList(eventList: List<Event>) {
        adapter = MessagesAdapter(eventList, emptyView, avatarUrl)
        msg_list.adapter = adapter
        msg_list.smoothScrollToPosition(adapter.itemCount - 1)
    }

    fun addMessageToList(event: Event) {
        adapter.updateEventList(event)
        msg_list.smoothScrollToPosition(adapter.itemCount - 1)
    }
}
