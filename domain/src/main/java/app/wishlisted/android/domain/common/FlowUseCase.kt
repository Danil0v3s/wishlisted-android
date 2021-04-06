package app.wishlisted.android.domain.common

import kotlinx.coroutines.flow.Flow

/**
 * Use case with a Flow return type and params.
 */
interface FlowUseCase<out Type, in Params> {
    operator fun invoke(params: Params): Flow<Type>
}

/**
 * Use case with a Flow return type.
 */
interface FlowUseCaseWithoutParams<out Type> {
    operator fun invoke(): Flow<Type>
}
