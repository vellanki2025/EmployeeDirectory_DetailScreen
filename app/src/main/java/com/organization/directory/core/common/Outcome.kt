package com.organization.directory.core.common

/**
 * A sealed class representing the outcome of an operation, which can be either a success or a failure.
 */
sealed class Outcome<out T> {

    /**
     * Represents a successful result state with data.
     *
     * @param data The data associated with the success state.
     */
    class Success<out T>(val data: T) : Outcome<T>()

    /**
     * Represents an error result state with an error message.
     *
     * @param message The error message associated with the error state.
     */
    class Error(val message: String?) : Outcome<Nothing>()
}