package io.github.pps5.sprint.feature.main.home.view.binder

import io.github.pps5.sprint.databinding.ItemGoalBinding
import io.github.pps5.sprint.domain.goal.Goal

fun ItemGoalBinding.update(
    newGoal: Goal,
    onComplete: (Goal) -> Unit
) {
    period.text = newGoal.getDateString()
    newGoal.title.unwrap { goalTitle ->
        goal.setText(goalTitle.value)
    }
    complete.isEnabled = newGoal.isSet()
    complete.setOnClickListener { onComplete(newGoal) }
}
