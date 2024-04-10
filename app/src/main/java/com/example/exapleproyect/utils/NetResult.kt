package com.example.exapleproyect.utils

sealed class NetResult<out T> {

    data class Success<out T>(val data: T): NetResult<T>()

    data class Error(val errorMessage: String, val errorCode : String? = null) : NetResult<Nothing>() {
        init {
            println("NET ERROR :$errorMessage")
        }
    }
}