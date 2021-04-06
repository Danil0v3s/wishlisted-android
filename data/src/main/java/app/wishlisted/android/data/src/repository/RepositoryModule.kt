package app.wishlisted.android.data.src.repository

import app.wishlisted.android.data.src.repository.game.GameRepositoryImpl
import app.wishlisted.android.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsGameRepository(impl: GameRepositoryImpl): GameRepository
}
