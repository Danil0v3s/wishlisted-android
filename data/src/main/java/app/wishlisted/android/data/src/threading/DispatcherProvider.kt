package app.wishlisted.android.data.src.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val computation: CoroutineDispatcher
}

class ApplicationDispatcherProvider @Inject constructor() : DispatcherProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main.immediate

    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
}
