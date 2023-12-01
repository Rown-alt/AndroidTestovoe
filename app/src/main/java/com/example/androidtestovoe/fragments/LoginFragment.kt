package com.example.androidtestovoe.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidtestovoe.databinding.FragmentLoginBinding
import com.example.androidtestovoe.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<LoginViewModel>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        val sharedPreferences = requireActivity().getSharedPreferences("TokenBase", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")

        if (token != "" && token != "null"){
            val action = LoginFragmentDirections.actionLoginFragmentToPaymentFragment()
            findNavController().navigate(action)
        }

        val editor = sharedPreferences.edit()

        // Убираем клавиатуру, фокус
        binding.loginEt.setOnEditorActionListener { textView, i, keyEvent ->
            requireActivity().hideKeyboard(textView)
            textView.clearFocus()
            true
        }

        // При нажатии на любую область прячем клавиатуру
        binding.root.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.clearFocus()
                    requireActivity().hideKeyboard(view)
                }
            }
            false
        }

        // Логика для кнопки отправления данных на проверку
        binding.sendBtn.setOnClickListener {
            if (binding.loginEt.text.toString() != ""){
                viewModel.loginUser(binding.loginEt.text.toString(), binding.passwordEt.text.toString())
                binding.progress.show()
                binding.progress.visibility = View.VISIBLE
                binding.sendBtn.visibility = View.INVISIBLE
            }
            else{
                Toast.makeText(requireContext(), "Enter your login in the field, please", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.token.observe(viewLifecycleOwner){
            val token =  it.response?.token.toString()
            Log.d("TokenApi", token)
            if (it.success == "false"){
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                binding.progress.visibility = View.INVISIBLE
                binding.sendBtn.visibility = View.VISIBLE
            }
            if (token != "" && token != "null"){
                editor.putString("token", token)
                editor.apply()
                val action = LoginFragmentDirections.actionLoginFragmentToPaymentFragment()
                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    // Сохраняем состояние элементов перед изменением конфига
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("progressState", binding.progress.isShown)
    }

    // Восстанавливаем состояние элементов после изменения конфига
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null){
            if (savedInstanceState.getBoolean("progressState")){
                binding.progress.show()
            }
        }
    }

    // Функция-расширение для убирания клавиатуры
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}