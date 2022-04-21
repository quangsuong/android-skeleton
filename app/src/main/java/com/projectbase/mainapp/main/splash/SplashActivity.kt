package com.projectbase.mainapp.main.splash

import android.os.Bundle
import android.os.Handler
import com.projectbase.R
import com.projectbase.base.ui.BaseActivity
import com.projectbase.mainapp.main.login.LoginActivity

class SplashActivity : BaseActivity() {

    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startMainActivity()
        }, SPLASH_DISPLAY_LENGTH)
    }

    private fun startMainActivity() {
        val intent = LoginActivity.getIntent(this)
        startActivity(intent)
        finish()
    }
}
