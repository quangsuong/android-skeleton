package com.projectbase.mainapp.main

import android.os.Bundle
import com.projectbase.R
import com.projectbase.base.ui.BaseActivity
import com.projectbase.mainapp.main.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name ?: "MainActivity::class.java.name"
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        handleObservable()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment(), HomeFragment.TAG)
            .commit()
    }

    private fun handleObservable() {

    }
}
