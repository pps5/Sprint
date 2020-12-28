package io.github.pps5.sprint.domain.valueobject

class GoalTitle private constructor(
    val value: String
) {
    companion object {
        fun of(title: String): GoalTitle? {
            return if (title.isBlank()) {
                null
            } else {
                GoalTitle(title)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GoalTitle

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "GoalTitle(value='$value')"
    }
}