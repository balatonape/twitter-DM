package com.tdm.mbtwitterdm.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tdm.mbtwitterdm.R
import com.tdm.mbtwitterdm.ui.adapters.FollowerAdapter
import com.tdm.mbtwitterdm.ui.viewmodel.FollowerScreenViewModel
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.followers_screen.*
import kotlinx.android.synthetic.main.message_screen.*

class FollowersScreen : AppCompatActivity() {
    lateinit var viewModel: FollowerScreenViewModel
    lateinit var adapter: FollowerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.followers_screen)

        friends_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        friends_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel = FollowerScreenViewModel()
        viewModel.errors?.observe(this, Observer { showSnackBar(it) })
        viewModel.users?.observe(this, Observer { updateUsersList(it) })

        viewModel.getFriendsList()
    }

    fun showSnackBar(string: CharSequence?) {
        Snackbar.make(parentLayout, string ?: "Error", Snackbar.LENGTH_LONG).show()
    }

    fun updateUsersList(userList: List<User>?) {
        adapter = FollowerAdapter(baseContext, userList, empty_view)
        friends_list.adapter = adapter
        if (adapter.itemCount > 0) {
            friends_list.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }
}
