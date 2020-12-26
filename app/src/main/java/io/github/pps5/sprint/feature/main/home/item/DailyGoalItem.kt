package io.github.pps5.sprint.feature.main.home.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ItemDailyGoalBinding
import io.github.pps5.sprint.domain.goal.DailyGoal

class DailyGoalItem(
    private val dailyGoals: List<DailyGoal>,
) : BindableItem<ItemDailyGoalBinding>() {

    override fun bind(viewBinding: ItemDailyGoalBinding, position: Int) {
        // todo: bind item
    }

    override fun getLayout(): Int {
        return R.layout.item_daily_goal
    }

    override fun initializeViewBinding(view: View): ItemDailyGoalBinding {
        return ItemDailyGoalBinding.bind(view)
    }
}