package com.example.androidtestovoe.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtestovoe.databinding.ItemPaymentBinding
import com.example.domain.models.PaymentResponse

class PaymentAdapter: RecyclerView.Adapter<PaymentAdapter.ResultViewHolder>() {
    private var payments: List<PaymentResponse.ResponsePayment> = listOf()

    class ResultViewHolder(binding: ItemPaymentBinding): RecyclerView.ViewHolder(binding.root) {
        private val title = binding.title
        private val amount = binding.amount
        fun bind(payment: PaymentResponse.ResponsePayment) {
            title.text = payment.title
            if (payment.amount.toString() != "null"){
                amount.text = payment.amount.toString()
            }
            else{
                amount.text = "Secret amount"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val views = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(views)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(payments[position])
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPayments(payments: List<PaymentResponse.ResponsePayment>){
        this.payments = payments
        notifyDataSetChanged()
    }
}