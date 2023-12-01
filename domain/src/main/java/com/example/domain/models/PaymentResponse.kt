package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class PaymentResponse (
    @SerializedName("success"  ) var success  : String?             = null,
    @SerializedName("response" ) var response : List<ResponsePayment> = listOf()
//    @SerializedName("error") var error: ErrorPayment =  ErrorPayment()
) {
    data class ResponsePayment (
        @SerializedName("id"      ) var id      : Int?    = null,
        @SerializedName("title"   ) var title   : String? = null,
        @SerializedName("amount"  ) var amount  : Any?    = null,
        @SerializedName("created" ) var created : Int?    = null
    )

    data class ErrorPayment (
        @SerializedName("error_code" ) var errorCode : Int?    = null,
        @SerializedName("error_msg"  ) var errorMsg  : String? = null
    )
}