package com.dungLv.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dungLv.base.BaseBindingActivity
import com.dunglv.appstory.R
import com.dunglv.appstory.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {

    var navHostFragment: NavHostFragment? = null
    var navController: NavController? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment!!.navController

    }

    override fun setupData() {
        viewModel.initData(this)
        viewModel.getAllBookmark()
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }


}