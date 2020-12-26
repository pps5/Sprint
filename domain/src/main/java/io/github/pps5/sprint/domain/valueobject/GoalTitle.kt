package io.github.pps5.sprint.domain.valueobject

class GoalTitle constructor(
    val value: String
) {

    init {
        require(value.isNotBlank()) { "Goal title cannot be blank" }
    }
}