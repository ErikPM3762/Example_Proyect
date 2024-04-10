package com.example.exapleproyect.utils

import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun <T>Throwable.toNetworkResult(): Response<T> =
    Response.error(500, getGenericError().toResponseBody(null))

fun getGenericError() = "Ups problema con el servidor"


fun <T, R> Response<T>.parse(parseMethod : (T) -> R): NetResult<R>{
    val responseData = body()
    return if(isSuccessful && responseData != null)
        try {
            NetResult.Success(parseMethod(responseData))
        } catch (e : Exception){
            NetResult.Error(getGenericError())
        }else {
        NetResult.Error(getGenericError())
    }
}