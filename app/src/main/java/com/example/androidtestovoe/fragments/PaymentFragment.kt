package com.example.androidtestovoe.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtestovoe.adapters.PaymentAdapter
import com.example.androidtestovoe.databinding.FragmentPaymentsBinding
import com.example.androidtestovoe.viewModels.PaymentViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentFragment: Fragment() {
    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!
    private val adapter = PaymentAdapter()
    private val viewModel by viewModel<PaymentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(layoutInflater)
        val sharedPreferences = requireActivity().getSharedPreferences("TokenBase", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")
        viewModel.getPayments(token!!)

        viewModel.payments.observe(viewLifecycleOwner){
            Log.d("Payments", it.toString())
            Log.d("Payments", token)
            adapter.setPayments(it.response)
        }
        binding.backBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("token", "")
            editor.apply()
            val action = PaymentFragmentDirections.actionPaymentFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.payments.adapter = adapter
        return binding.root
    }
}