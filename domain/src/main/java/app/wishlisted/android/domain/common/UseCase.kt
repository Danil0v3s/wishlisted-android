package app.wishlisted.android.domain.src.common

/**
 * Use case with a return type and params.
 */
interface UseCase<out Type, in Params> {
    operator fun invoke(params: Params): Type
}

/**
 * Use case with a return type.
 */
interface UseCaseWithoutParams<out Type> {
    operator fun invoke(): Type
}
