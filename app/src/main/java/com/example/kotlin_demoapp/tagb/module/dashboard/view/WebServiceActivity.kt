package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.activity.components.ComponentActivity
import com.example.kotlin_demoapp.databinding.ActivityWebServicesBinding
import com.example.kotlin_demoapp.screens.AccountSecurity
import com.example.kotlin_demoapp.screens.MeetingScheduler
import com.example.kotlin_demoapp.screens.ParkingCitation
import com.example.kotlin_demoapp.screens.go_tour.GoTourLogin
import com.example.kotlin_demoapp.tagb.base_classes.BaseActivity
import com.example.kotlin_demoapp.tagb.helper.launchActivity
import com.example.kotlin_demoapp.tagb.helper.launchActivityWithClearTask
import com.example.kotlin_demoapp.tagb.helper.setStatusBarLightAppearance
import com.example.kotlin_demoapp.tagb.module.authentication.view.OnBoardActivity
import com.example.kotlin_demoapp.tagb.module.dashboard.viewModel.WebServicesViewModel
import com.example.kotlin_demoapp.tagb.utils.UserPreference

class WebServiceActivity : BaseActivity<ActivityWebServicesBinding, WebServicesViewModel>() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainFragment) as? NavHostFragment)?.navController
    }

    override fun setViewModel(): WebServicesViewModel? =
        ViewModelProvider(this)[WebServicesViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_web_services

    override fun setUpView() {
        setStatusBarLightAppearance(false)
        binding.viewModel = viewModel
        navController?.let { binding.bottomNavView.setupWithNavController(it) }
        setNavigationHeader()
        addObservers()
    }

    private fun setNavigationHeader() {
        with(binding.navDrawer.getHeaderView(0)) {
            findViewById<TextView>(R.id.txtUser).text = UserPreference.name
        }
    }

    private fun addObservers() {
        binding.navDrawer.apply {
            bringToFront()
            setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.components -> launchActivity<ComponentActivity>()
                    R.id.screen_security -> launchActivity<AccountSecurity>()
                    R.id.screen_parkingCitation -> launchActivity<ParkingCitation>()
                    R.id.screen_goTour -> launchActivity<GoTourLogin>()
                    R.id.logout -> logoutUser()
                }
                binding.drawer.closeDrawer(GravityCompat.START)
                true
            }
            setCheckedItem(R.id.user)
        }
        viewModel.isDrawerOpen.observe(this) {
            if (binding.drawer.isDrawerVisible(GravityCompat.START)) {
                binding.drawer.closeDrawer(GravityCompat.START)
            } else {
                binding.drawer.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun logoutUser() {
        launchActivityWithClearTask<OnBoardActivity>()
        UserPreference.clearAll()
    }
}