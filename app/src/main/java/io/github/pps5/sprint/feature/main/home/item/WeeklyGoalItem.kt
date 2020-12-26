package io.github.pps5.sprint.feature.main.home.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ItemWeeklyGoalBinding
import io.github.pps5.sprint.domain.goal.WeeklyGoal

class WeeklyGoalItem(
    private val weeklyGoal: WeeklyGoal,
) : BindableItem<ItemWeeklyGoalBinding>() {

    override fun bind(viewBinding: ItemWeeklyGoalBinding, position: Int) {
        viewBinding.week.text = weeklyGoal.getDateString()
        viewBinding.goal.visibility = if (weeklyGoal.isSet()) View.VISIBLE else View.GONE
        viewBinding.setGoal.visibility = if (weeklyGoal.isSet()) View.GONE else View.VISIBLE
        weeklyGoal.title.unwrap { goalTitle ->
            viewBinding.goal.text = goalTitle.value
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_weekly_goal
    }

    override fun initializeViewBinding(view: View): ItemWeeklyGoalBinding {
        return ItemWeeklyGoalBinding.bind(view)
    }

}