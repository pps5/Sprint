package io.github.pps5.sprint.feature.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.FragmentHomeBinding
import io.github.pps5.sprint.feature.main.home.view.binder.bind
import io.github.pps5.sprint.feature.main.home.view.binder.setupWith
import io.github.pps5.sprint.feature.main.home.view.item.DailyGoalItem
import io.github.pps5.sprint.util.property.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val dailyGoalPagerAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dailyGoals.setupWith(dailyGoalPagerAdapter)
        viewModel.state
            .onEach { state -> updateViews(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateViews(state: HomeViewModel.State) {
        binding.monthly.bind(
            state.monthlyGoal,
            viewModel::onToggleComplete,
            viewModel::onGoalTitleUpdated
        )
        binding.weekly.bind(
            state.weeklyGoal,
            viewModel::onToggleComplete,
            viewModel::onGoalTitleUpdated
        )
        val dailyGoalItems = state.dailyGoals.map { goal ->
            DailyGoalItem(goal, viewModel::onToggleComplete, viewModel::onGoalTitleUpdated)
        }
        dailyGoalPagerAdapter.update(dailyGoalItems)
        dailyGoalPagerAdapter.notifyDataSetChanged()
    }
}