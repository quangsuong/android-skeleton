package vn.impl.projectbase.mainapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

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
            }
        })
    }
}
