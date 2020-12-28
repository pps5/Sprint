package io.github.pps5.sprint.feature.main.home.view.binder

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import io.github.pps5.sprint.databinding.ItemGoalBinding
import io.github.pps5.sprint.domain.entity.goal.Goal

fun ItemGoalBinding.update(
    newGoal: Goal,
    onCompleteListener: (Goal) -> Unit,
    onTitleUpdatedListener: (Goal, String) -> Unit,
) {
    period.text = newGoal.getDateString()
    newGoal.title.unwrap { goalTitle ->
        goal.setText(goalTitle.value)
    }
    goal.setListeners(newGoal, onTitleUpdatedListener)
    complete.isEnabled = newGoal.isSet()
    complete.setOnClickListener { onCompleteListener(newGoal) }
}

private fun EditText.setListeners(
    goal: Goal,
    onModifiedTitle: (Goal, String) -> Unit,
) {
    this.setOnEditorActionListener { v, actionId, _ ->
        if (actionId != EditorInfo.IME_ACTION_DONE) {
            return@setOnEditorActionListener false
        }
        v.context.getSystemService(InputMethodManager::class.java)
            .hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
        true
    }

    this.setOnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            onModifiedTitle(goal, (v as EditText).text.toString())
        }
    }

}