package com.example.androidtestovoe.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.LoginData
import com.example.domain.models.PaymentResponse
import com.example.domain.repository.Repository
import kotlinx.coroutines.launch

class PaymentViewModel(private val repository: Repository): ViewModel() {
    var payments = MutableLiveData<PaymentResponse>()

    // Payments
    fun getPayments(token: String){
        viewModelScope.launch {
            try{
                val request = repository.getPayments(token)
                request.onSuccess {
                    payments.postValue(it)
                }
                request.onFailure {
                    Log.d("CheckEmailRequestFailure", it.message.toString())
                }
            }
            catch (exc: Exception){
                exc.printStackTrace()
            }
        }
    }
}