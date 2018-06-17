package com.tdm.mbtwitterdm;

import android.app.Application;
import android.util.Log;

import com.tdm.mbtwitterdm.data.MyTwitterApiClient;
import com.tdm.mbtwitterdm.data.UserInfo;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterSession;

public class TwitterApplication extends Application {

    TwitterSession session;
    MyTwitterApiClient apiClient;
    UserInfo userInfo;

    private static TwitterApplication application;

    //    Making it as singleton to access the userInfo and session in other places
    public static TwitterApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //        Twitter initialization
        Twitter.initialize(this);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(
                        getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public TwitterSession getSession() {
        return session;
    }

    public void setSession(TwitterSession twitterSession) {
        session = twitterSession;
        userInfo = new UserInfo(session.getUserId(), session.getUserName());
        apiClient = new MyTwitterApiClient(session);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public MyTwitterApiClient getApiClient() {
        return apiClient;
    }

}
