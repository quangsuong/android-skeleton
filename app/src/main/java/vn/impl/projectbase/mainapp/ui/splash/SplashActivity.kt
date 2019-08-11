package vn.impl.projectbase.mainapp.ui.splash

import android.os.Bundle
import android.os.Handler
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseActivity
import vn.impl.projectbase.mainapp.ui.login.LoginActivity

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
