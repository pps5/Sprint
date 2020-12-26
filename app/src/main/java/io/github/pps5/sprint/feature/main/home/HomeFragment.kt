package io.github.pps5.sprint.feature.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.FragmentHomeBinding
import io.github.pps5.sprint.feature.main.home.item.DailyGoalItem
import io.github.pps5.sprint.feature.main.home.item.MonthlyGoalItem
import io.github.pps5.sprint.feature.main.home.item.WeeklyGoalItem
import io.github.pps5.sprint.property.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.container.adapter = adapter

        viewModel.state
            .onEach(this::updateViews)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateViews(state: HomeViewModel.State) {
        adapter.clear()
        adapter.addAll(
            listOf(
                MonthlyGoalItem(state.monthlyGoal),
                WeeklyGoalItem(state.weeklyGoal),
                DailyGoalItem(state.dailyGoals),
            )
        )
    }
}