package com.example.githubapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.githubapp.R
import com.example.githubapp.data.pref.SharedPref
import com.example.githubapp.ui.login.LoginActivity
import com.example.githubapp.ui.repo.RepositoryActivity

class SplashActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        sharedPref = SharedPref(this)
        val accessToken = sharedPref.accessToken

        setSplashTimer(accessToken.orEmpty())
    }

    private fun setSplashTimer(accessToken: String) {
        timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                if (sharedPref.accessToken.isNullOrBlank()) {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    RepositoryActivity.startActivity(this@SplashActivity, accessToken)
                }
            }

        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}