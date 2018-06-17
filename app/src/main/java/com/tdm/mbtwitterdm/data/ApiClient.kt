package com.tdm.mbtwitterdm.data

import com.twitter.sdk.android.core.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @GET("/1.1/users/show.json")
    fun getUserInfo(@Query("user_id") userId: Long): Call<User>

    @GET("/1.1/friends/list.json?cursor=-1&skip_status=true&include_user_entities=false")
    fun getAllFriendsList(@Query("user_id") userId: Long, @Query("screen_name") scrName: String): Call<FriendListResponse>

    @POST("/1.1/direct_messages/events/new.json")
    @Headers("Content-Type: application/json")
    fun sendMessage(@Body event: NewMessageEvent): Call<SentMessage>

    @GET("/1.1/direct_messages/events/list.json")
    fun getMessageList(): Call<EventsList>


}