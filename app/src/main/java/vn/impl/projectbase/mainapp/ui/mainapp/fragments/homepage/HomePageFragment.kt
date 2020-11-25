package vn.impl.projectbase.mainapp.ui.mainapp.fragments.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import vn.impl.projectbase.R
import vn.impl.projectbase.base.ui.BaseFragment
import javax.inject.Inject

class HomePageFragment : BaseFragment() {

    companion object {
        private val TAG = HomePageFragment::class.java.name
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mPageHomeViewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPageHomeViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomePageViewModel::class.java)

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
