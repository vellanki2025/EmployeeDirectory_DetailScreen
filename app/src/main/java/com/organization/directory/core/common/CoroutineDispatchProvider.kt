package com.organization.directory.core.common

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface providing coroutines dispatchers for different contexts.
 */
interface CoroutineDispatchProvider {

    /**
     * The [CoroutineDispatcher] for the main thread.
     */
    val main: CoroutineDispatcher

    /**
     * The [CoroutineDispatcher] for the IO thread, use for disk or network IO operations.
     */
    val io: CoroutineDispatcher

    /**
     * The [CoroutineDispatcher] for the default thread, use for CPU-intensive operations.
     */
    val default: CoroutineDispatcher
}