package io.github.pps5.sprint.feature.main.home.view.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.pps5.sprint.R
import io.github.pps5.sprint.databinding.ItemGoalBinding
import io.github.pps5.sprint.domain.entity.goal.Goal
import io.github.pps5.sprint.feature.main.home.view.binder.update

class DailyGoalItem(
    private val goal: Goal,
    private val onCompleteListener: (Goal) -> Unit,
    private val onTitleUpdatedListener: (Goal, String) -> Unit,
) : BindableItem<ItemGoalBinding>() {
    override fun bind(viewBinding: ItemGoalBinding, position: Int) {
        viewBinding.update(goal, onCompleteListener, onTitleUpdatedListener)
    }

    override fun getLayout(): Int {
        return R.layout.item_goal
    }

    override fun initializeViewBinding(view: View): ItemGoalBinding {
        return ItemGoalBinding.bind(view)
    }
}