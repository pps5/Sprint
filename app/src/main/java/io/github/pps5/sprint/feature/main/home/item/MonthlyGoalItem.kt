package io.github.pps5.sprint.feature.main.home.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ItemMonthlyGoalBinding
import io.github.pps5.sprint.domain.goal.MonthlyGoal

class MonthlyGoalItem(
    private val monthlyGoal: MonthlyGoal,
) : BindableItem<ItemMonthlyGoalBinding>() {

    override fun bind(viewBinding: ItemMonthlyGoalBinding, position: Int) {
        viewBinding.yearMonth.text = monthlyGoal.getDateString()
        viewBinding.goal.visibility = if (monthlyGoal.isSet()) View.VISIBLE else View.GONE
        viewBinding.setGoal.visibility = if (monthlyGoal.isSet()) View.GONE else View.VISIBLE
        monthlyGoal.title.unwrap { goalTitle ->
            viewBinding.goal.text = goalTitle.value
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_monthly_goal
    }

    override fun initializeViewBinding(view: View): ItemMonthlyGoalBinding {
        return ItemMonthlyGoalBinding.bind(view)
    }

}