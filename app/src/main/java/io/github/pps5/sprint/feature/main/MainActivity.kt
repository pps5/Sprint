package io.github.pps5.sprint.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ActivityMainBinding
import io.github.pps5.sprint.feature.main.history.HistoryFragment
import io.github.pps5.sprint.feature.main.home.HomeFragment
import io.github.pps5.sprint.feature.main.stats.StatsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWithNavController()

        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragmentContainer.id, HomeFragment())
            .commit()
    }

    private fun setupWithNavController() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.stats -> StatsFragment()
                R.id.history -> HistoryFragment()
                else -> throw RuntimeException()
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.mainFragmentContainer.id, fragment)
                .commit()
            true
        }
    }
}