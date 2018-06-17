package com.tdm.mbtwitterdm.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.twitter.sdk.android.core.models.User

data class FriendListResponse(
        @SerializedName("users") @Expose
        val users: List<User>
)