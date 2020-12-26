package io.github.pps5.sprint.domain.valueobject

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GoalTitleTest {

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "　"])
    fun `GoalTitle throws IllegalArgumentException when title is blank`(title: String) {
        assertThrows<IllegalArgumentException> { GoalTitle(title) }
    }

}