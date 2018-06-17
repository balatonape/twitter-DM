package com.tdm.mbtwitterdm.data

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.services.SearchService
import com.twitter.sdk.android.core.services.StatusesService

class MyTwitterApiClient(session: TwitterSession) : TwitterApiClient(session) {

    val customService: ApiClient
        get() = getService(ApiClient::class.java)

    override fun getStatusesService(): StatusesService {
        return super.getStatusesService()
    }

    override fun getSearchService(): SearchService {
        return super.getSearchService()
    }
}