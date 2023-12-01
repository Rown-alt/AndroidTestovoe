package com.example.androidtestovoe.di


import com.example.androidtestovoe.viewModels.LoginViewModel
import com.example.androidtestovoe.viewModels.PaymentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        PaymentViewModel(get())
    }
}