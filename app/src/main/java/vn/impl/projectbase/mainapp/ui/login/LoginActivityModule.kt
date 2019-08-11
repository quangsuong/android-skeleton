package vn.impl.projectbase.mainapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import vn.impl.projectbase.base.injection.ActivityScope
import vn.impl.projectbase.base.injection.ViewModelKey

@Module
abstract class LoginActivityModule {

    /**
     * @see https://medium.com/@marco_cattaneo/android-viewmodel-and-factoryprovider-good-way-to-manage-it-with-dagger-2-d9e20a07084c
     * */
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMainViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @ActivityScope
    abstract fun appCompatActivity(loginActivity: LoginActivity): AppCompatActivity

}
