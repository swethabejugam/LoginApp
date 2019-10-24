package com.swetha.loginapp.login

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success(val data: Boolean) : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()

//    override fun toString(): String {
//        return when (this) {
//            is Success<*> -> "Success[data=$data]"
//            is Error -> "Error[exception=$exception]"
//        }
//    }
}
