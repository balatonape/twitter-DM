package com.tdm.mbtwitterdm.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tdm.mbtwitterdm.R
import com.tdm.mbtwitterdm.data.response.Event
import com.tdm.mbtwitterdm.ui.adapters.MessagesAdapter
import com.tdm.mbtwitterdm.ui.viewmodel.MessageScreenViewModel
import kotlinx.android.synthetic.main.message_screen.*

class MessageScreen : AppCompatActivity() {

    lateinit var viewModel: MessageScreenViewModel
    lateinit var avatarUrl: String
    lateinit var adapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_screen)
        val title: String = intent.getStringExtra("usrName")
        setTitle(title)

        val usrId: String = intent.getStringExtra("usrId")
        avatarUrl = intent.getStringExtra("avatarUrl")
        msg_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        viewModel = MessageScreenViewModel(userId = usrId)
        viewModel.getMessageList(usrId)
        viewModel.errors?.observe(this, Observer { showSnackBar(it) })
        viewModel.events?.observe(this, Observer { updateMessageList(it) })
        viewModel.sentMsgEvent?.observe(this, Observer { addMessageToList(it) })

        send_msg.setOnClickListener { viewModel.sendMessageEvent(usrId, msg_text.text.toString()) }
    }

    fun showSnackBar(string: CharSequence?) {
        Snackbar.make(parentLayout, string ?: "Error", Snackbar.LENGTH_LONG).show()
    }

    fun updateMessageList(eventList: List<Event>?) {
        adapter = MessagesAdapter(eventList, emptyView, avatarUrl)
        msg_list.adapter = adapter
        if (adapter.itemCount > 0) {
            msg_list.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }

    fun addMessageToList(event: Event?) {
        msg_text.setText("")
        adapter.updateEventList(event)
        msg_list.smoothScrollToPosition(adapter.itemCount - 1)
    }
}
