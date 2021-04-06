package br.com.firstsoft.historiconotificacoes.domain.src.common

/**
 * Suspendable use case with a return type and params.
 */
interface SuspendUseCase<out Type, in Params> {
    suspend operator fun invoke(params: Params): Type
}

/**
 * Suspendable use case with a return type.
 */
interface SuspendUseCaseWithoutParams<out Type> {
    suspend operator fun invoke(): Type
}
