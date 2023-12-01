package com.example.androidtestovoe.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.LoginData
import com.example.domain.models.LoginResponse
import com.example.domain.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository): ViewModel() {
    var token = MutableLiveData<LoginResponse>()
    // Login
    fun loginUser(login: String, password: String){
        viewModelScope.launch {
            try{
                val request = repository.loginUser(LoginData(login, password))
                request.onSuccess {
                    token.postValue(it)
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