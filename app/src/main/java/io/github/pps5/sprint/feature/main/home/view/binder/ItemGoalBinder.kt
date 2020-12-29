package io.github.pps5.sprint.feature.main.home.view.binder

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import io.github.pps5.sprint.databinding.ItemGoalBinding
import io.github.pps5.sprint.domain.entity.goal.Goal

fun ItemGoalBinding.bind(
    newGoal: Goal,
    onCompleteListener: (Goal) -> Unit,
    onTitleUpdatedListener: (Goal, String) -> Unit,
) {
    fun bindTitle() {
        val newTitle = newGoal.title.getOrNull()
        if (newTitle == null) {
            goal.text.clear()
        } else {
            goal.setText(newTitle.value)
        }
    }

    fun setEditTextListeners() {
        goal.setOnEditorActionListener { v, actionId, _ ->
            if (actionId != EditorInfo.IME_ACTION_DONE) {
                return@setOnEditorActionListener false
            }
            v.context.getSystemService(InputMethodManager::class.java)
                .hideSoftInputFromWindow(v.windowToken, 0)
            v.clearFocus()
            true
        }

        goal.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                onTitleUpdatedListener(newGoal, (v as EditText).text.toString())
            }
        }
    }

    fun setupCompleteButton() {
        complete.isEnabled = newGoal.isSet()
        
        complete.setOnClickListener { onCompleteListener(newGoal) }
    }


    period.text = newGoal.getDateString()
    bindTitle()
    setupCompleteButton()
    setEditTextListeners()
}
