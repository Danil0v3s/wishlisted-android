package app.wishlisted.android.domain.usecase

import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsFetchGameDealsUseCase(impl: FetchGameDealsUseCaseImpl): FetchGameDealsUseCase
}
