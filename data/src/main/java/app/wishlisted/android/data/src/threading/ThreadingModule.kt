package app.wishlisted.android.data.src.threading

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ThreadingModule {
    @Binds
    abstract fun bindDispatcherProvider(impl: ApplicationDispatcherProvider): DispatcherProvider
}
