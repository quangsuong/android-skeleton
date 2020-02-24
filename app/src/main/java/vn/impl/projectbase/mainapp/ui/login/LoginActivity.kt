package vn.impl.projectbase.mainapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseActivity
import vn.impl.projectbase.mainapp.ui.mainapp.MainActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val loginViewModel: LoginViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        handleObservable()
    }

    private fun initViews() {
        button_test_api.setOnClickListener {
            loginViewModel.checkGetApi(0)
        }
    }

    private fun handleObservable() {
        // handler test api request
        loginViewModel.getTestApi().observe(this, Observer {
            it?.let {
                api_result.text = it.accessToken
            }
        })

        // handler errors
        loginViewModel.getErrorObserver().observe(this, Observer {
            it?.let {
                api_result.text = it
            }
        })

        // handler status saved user into database
        loginViewModel.getIsSaveUser().observe(this, Observer {
            it?.let {
                status.text = if (it) "save succeeded" else "save failed"
                startActivity(MainActivity.getIntent(this))
                finish()
            }
        })
    }
}
