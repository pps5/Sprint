package io.github.pps5.sprint.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.github.pps5.sprint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWithNavController()
    }

    private fun setupWithNavController() {
        val navController = (supportFragmentManager
            .findFragmentById(binding.mainNavHostFragment.id) as NavHostFragment)
            .navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
}