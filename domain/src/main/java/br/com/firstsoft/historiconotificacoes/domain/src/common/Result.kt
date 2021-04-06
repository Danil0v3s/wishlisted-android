package br.com.firstsoft.historiconotificacoes.domain.src.common

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val error: Throwable) : Result<T>()

    inline fun fold(onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit) {
        when (this) {
            is Success -> onSuccess(value)
            is Failure -> onFailure(error)
        }
    }

    inline fun <R> map(mapper: (T) -> R): Result<R> {
        return when (this) {
            is Success -> Success(mapper(value))
            is Failure -> Failure(error)
        }
    }

    inline fun onSuccess(onSuccess: (T) -> Unit): Result<T> {
        if (this is Success) onSuccess(value)
        return this
    }

    inline fun onFailure(onFailure: (Throwable) -> Unit): Result<T> {
        if (this is Failure) onFailure(error)
        return this
    }

    fun getOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }

    @Throws(Throwable::class)
    fun getOrThrow(): T {
        when (this) {
            is Success -> return value
            is Failure -> throw error
        }
    }

    fun exceptionOrNull(): Throwable? {
        return when (this) {
            is Failure -> error
            is Success -> null
        }
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure
}
