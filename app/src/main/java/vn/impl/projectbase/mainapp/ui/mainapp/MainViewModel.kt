package vn.impl.projectbase.mainapp.ui.mainapp

import androidx.lifecycle.ViewModel
import vn.impl.projectbase.base.repository.user.UserRepository

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.name
    }

}
