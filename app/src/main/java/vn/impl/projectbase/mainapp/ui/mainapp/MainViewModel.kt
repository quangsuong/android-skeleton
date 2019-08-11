package vn.impl.projectbase.mainapp.ui.mainapp

import androidx.lifecycle.ViewModel
import vn.impl.projectbase.base.repository.user.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.name
    }

}
