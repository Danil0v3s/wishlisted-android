package app.wishlisted.android.domain.usecase

import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCase
import app.wishlisted.android.domain.usecase.game.FetchGameDealsUseCaseImpl
import app.wishlisted.android.domain.usecase.game.FetchGameDetailsUseCase
import app.wishlisted.android.domain.usecase.game.FetchGameDetailsUseCaseImpl
import app.wishlisted.android.domain.usecase.game.FetchGameStatusesUseCase
import app.wishlisted.android.domain.usecase.game.FetchGameStatusesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsFetchGameDealsUseCase(impl: FetchGameDealsUseCaseImpl): FetchGameDealsUseCase

    @Binds
    abstract fun bindsFetchGameStatusesUseCase(impl: FetchGameStatusesUseCaseImpl): FetchGameStatusesUseCase

    @Binds
    abstract fun bindsFetchGameDetails(impl: FetchGameDetailsUseCaseImpl): FetchGameDetailsUseCase
}
