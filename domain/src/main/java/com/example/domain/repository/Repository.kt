package com.example.domain.repository

import com.example.domain.models.LoginData
import com.example.domain.models.LoginResponse
import com.example.domain.models.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface Repository {
    suspend fun loginUser(@Body data: LoginData): Result<LoginResponse>
    suspend fun getPayments(@Header("token") token: String): Result<PaymentResponse>
}