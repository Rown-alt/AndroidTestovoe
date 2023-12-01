package com.example.domain.repository

import com.example.domain.api.AndroidApi
import com.example.domain.models.LoginData
import com.example.domain.models.LoginResponse
import com.example.domain.models.PaymentResponse

class RepositoryImpl(private val api: AndroidApi): Repository {
    override suspend fun loginUser(data: LoginData): Result<LoginResponse> {
        return api.loginUser(data)
    }

    override suspend fun getPayments(token: String): Result<PaymentResponse> {
        return api.getPayments(token)
    }
}