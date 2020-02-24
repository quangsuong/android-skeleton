package vn.impl.projectbase.mainapp.ui.mainapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseActivity
import vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage.HomePageFragment

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val mainViewModel: MainViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        handleObservable()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomePageFragment(), HomePageFragment.TAG).commit()
    }

    private fun handleObservable() {

    }
}
