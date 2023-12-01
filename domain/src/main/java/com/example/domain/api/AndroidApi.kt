package com.example.domain.api

import com.example.domain.models.LoginData
import com.example.domain.models.LoginResponse
import com.example.domain.models.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AndroidApi {
    @Headers("app-key: 12345",
        "v: 1")
    @POST("login")
    suspend fun loginUser(@Body data: LoginData): Result<LoginResponse>

    @Headers("app-key: 12345",
        "v: 1")
    @GET("payments")
    suspend fun getPayments(@Header("token") token: String): Result<PaymentResponse>
}