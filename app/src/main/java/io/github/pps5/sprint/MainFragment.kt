package io.github.pps5.sprint

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import io.github.pps5.sprint.databinding.FragmentMainBinding
import io.github.pps5.sprint.property.childNavController
import io.github.pps5.sprint.property.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainNavController: NavController by childNavController(R.id.main_nav_host_fragment)
    private val binding: FragmentMainBinding by viewBinding { FragmentMainBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigation.setupWithNavController(mainNavController)
    }
}
