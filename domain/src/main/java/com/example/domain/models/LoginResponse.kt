package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("success"  ) var success  : String?   = null,
    @SerializedName("response" ) var response : ResponseLogin? = ResponseLogin()

) {
    data class ResponseLogin (
        @SerializedName("token" ) var token : String? = null)
}