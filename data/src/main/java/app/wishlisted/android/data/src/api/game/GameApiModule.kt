package app.wishlisted.android.data.src.api.game

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GameApiModule {

    @Provides
    fun providesGameApi(retrofit: Retrofit) =
        retrofit.create(GameApi::class.java)
}