package com.tdm.mbtwitterdm.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tdm.mbtwitterdm.R
import com.tdm.mbtwitterdm.TwitterApplication
import com.twitter.sdk.android.core.*
import kotlinx.android.synthetic.main.twitter_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_login)

        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                TwitterApplication.getInstance()?.session = TwitterCore.getInstance().getSessionManager().getActiveSession()
                val messageScreen = Intent(baseContext, FollowersScreen::class.java)
                startActivity(messageScreen)
            }

            override fun failure(exception: TwitterException) {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        login_button.onActivityResult(requestCode, resultCode, data)
    }
}
