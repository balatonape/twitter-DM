package com.tdm.mbtwitterdm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tdm.mbtwitterdm.R
import com.tdm.mbtwitterdm.TwitterApplication
import com.tdm.mbtwitterdm.data.FriendListResponse
import com.tdm.mbtwitterdm.ui.adapters.FollowerAdapter
import kotlinx.android.synthetic.main.followers_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.followers_screen)

        friends_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        friends_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val call = TwitterApplication.getInstance().apiClient.customService.getAllFriendsList(
                TwitterApplication.getInstance().userInfo.usrId,
                TwitterApplication.getInstance().userInfo.usrScrName)

        call.enqueue(object : Callback<FriendListResponse> {
            override fun onResponse(call: Call<FriendListResponse>, response: Response<FriendListResponse>?) {
                val adapter = FollowerAdapter(baseContext, response?.body()?.users, empty_view)
                friends_list.adapter = adapter
            }

            override fun onFailure(call: Call<FriendListResponse>, t: Throwable) {
                println("******** failure")
            }
        })
    }
}
