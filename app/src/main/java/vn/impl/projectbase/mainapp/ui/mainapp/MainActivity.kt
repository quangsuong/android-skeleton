package vn.impl.projectbase.mainapp.ui.mainapp

import android.os.Bundle
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
