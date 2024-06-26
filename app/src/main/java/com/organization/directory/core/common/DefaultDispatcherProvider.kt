package com.organization.directory.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Class implementing [CoroutineDispatchProvider] interface to manage different contexts using the [Dispatchers] object.
 */
class DefaultDispatcherProvider @Inject constructor() : CoroutineDispatchProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}
