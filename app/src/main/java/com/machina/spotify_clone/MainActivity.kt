package com.machina.spotify_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.bottomSheetContainer.setOnClickListener {
            val intent = Intent(this, DetailTrackActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up)
        }
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment

        NavigationUI.setupWithNavController(
            binding.activityMainBtmNav,
            navHostFragment.navController)
    }
}