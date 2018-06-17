package com.tdm.mbtwitterdm.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tdm.mbtwitterdm.TwitterApplication
import com.tdm.mbtwitterdm.data.response.FriendListResponse
import com.twitter.sdk.android.core.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerScreenViewModel {
    private val userList = MutableLiveData<List<User>>()
    private val error = MutableLiveData<CharSequence>()

    val users: LiveData<List<User>>?
        get() = userList

    val errors: LiveData<CharSequence>
        get() = error

    fun getFriendsList() {
        val call = TwitterApplication.getInstance().apiClient.customService.getAllFriendsList(
                TwitterApplication.getInstance().userInfo.usrId,
                TwitterApplication.getInstance().userInfo.usrScrName)

        call.enqueue(object : Callback<FriendListResponse> {
            override fun onResponse(call: Call<FriendListResponse>, response: Response<FriendListResponse>?) {
                when {
                    response?.code() in 200..299 -> {
                        userList.value = response?.body()?.users
                    }
                    else -> error.value = "Unable to get UserList"
                }
            }

            override fun onFailure(call: Call<FriendListResponse>, t: Throwable) {
                error.value = "Unable to get UserList"
            }
        })
    }
}
