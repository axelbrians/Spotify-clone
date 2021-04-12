package com.machina.spotify_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.machina.spotify_clone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        setUpListener()
    }


    private fun setUpListener() {
        binding.activityMainBtmSheetContainer.setOnClickListener {
            val intent = Intent(this, DetailTrackActivity::class.java)
            startActivity(intent)
                                    // enter animation      exit animation
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up)
        }
    }

    /**
     * not using usual setup for Navigation Component in order to achieve
     * custom Animation when switching page within bottom nav
     * the custom Animation is added in option variable
    */
    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val option = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.fade_in)
                .setPopUpTo(navController.graph.startDestination, false)
                .build()

        binding.activityMainBtmNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> { navController.navigate(item.itemId, null, option) }
                R.id.searchFragment -> { navController.navigate(item.itemId, null, option) }
                R.id.libraryFragment -> { navController.navigate(item.itemId, null, option) }
            }
            true
            }

        binding.activityMainBtmNav.setOnNavigationItemReselectedListener { item ->
            return@setOnNavigationItemReselectedListener
        }

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}