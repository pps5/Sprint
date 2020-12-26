package io.github.pps5.sprint.domain.valueobject

sealed class Option<T> {

    data class Some<T>(val value: T) : Option<T>()

    class None<T> : Option<T>() {
        override fun equals(other: Any?): Boolean {
            return other is None<*>
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    companion object {
        fun <T> apply(value: T?): Option<T> {
            return if (value == null) {
                None()
            } else {
                Some(value)
            }
        }
    }

    fun unwrap(block: (T) -> Unit) {
        if (this is Some) {
            block(this.value)
        }
    }
}

