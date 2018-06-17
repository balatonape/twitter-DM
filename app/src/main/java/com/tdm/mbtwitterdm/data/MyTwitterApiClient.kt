package com.tdm.mbtwitterdm.data

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession

class MyTwitterApiClient(session: TwitterSession) : TwitterApiClient(session) {

    val customService: ApiClient
        get() = getService(ApiClient::class.java)
}