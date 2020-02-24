package vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseFragment
import vn.impl.projectbase.mainapp.ui.mainapp.MainViewModel

class HomePageFragment : BaseFragment() {

    companion object {
        val TAG: String = HomePageFragment::class.java.name
    }

    private val mPageHomeViewModel: HomePageViewModel by currentScope.viewModel(this)
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        handleObservable()

    }

    private fun initViews() {
        // init view
    }

    private fun handleObservable() {
        // handle observable
    }

}
