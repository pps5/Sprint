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
import io.github.pps5.sprint.feature.main.home.view.binder.setupWith
import io.github.pps5.sprint.feature.main.home.view.binder.update
import io.github.pps5.sprint.feature.main.home.view.item.DailyGoalItem
import io.github.pps5.sprint.property.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val dailyGoalPagerAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dailyGoals.setupWith(dailyGoalPagerAdapter)
        viewModel.state
            .onEach(this::updateViews)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateViews(state: HomeViewModel.State) {
        binding.monthly.update(state.monthlyGoal, viewModel::onCompleteGoal)
        binding.weekly.update(state.weeklyGoal, viewModel::onCompleteGoal)

        val dailyGoalItems = state.dailyGoals.map { DailyGoalItem(it, viewModel::onCompleteGoal) }
        dailyGoalPagerAdapter.update(dailyGoalItems)
        dailyGoalPagerAdapter.notifyDataSetChanged()
    }
}