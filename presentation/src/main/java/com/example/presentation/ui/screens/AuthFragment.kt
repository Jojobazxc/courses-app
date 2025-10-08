package com.example.presentation.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.util.Patterns
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAuthBinding
import com.example.presentation.ui.navigation.OnAuthSuccessListener

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var listener: OnAuthSuccessListener? = null

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAuthSuccessListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAuthBinding.bind(view)

        val latinFilter = InputFilter { source, start, end, dest, dstart, dend ->
            val regex = Regex("[a-zA-Z0-9@._-]+")
            if (source.isEmpty() || source.matches(regex)) {
                source
            } else {
                ""
            }
        }

        binding.mainBlock.emailField.filters = arrayOf(latinFilter)

        binding.mainBlock.authButton.isEnabled = false

        binding.mainBlock.emailField.addTextChangedListener { text ->
            validateEmail()
        }

        binding.mainBlock.passwordField.addTextChangedListener { text ->
            validateEmail()
        }

        binding.socialRow.okButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
            startActivity(intent)
        }

        binding.socialRow.vkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
            startActivity(intent)
        }

        binding.mainBlock.authButton.setOnClickListener {
            if (binding.mainBlock.authButton.isEnabled) {
                listener?.onAuthSuccess()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateEmail() {
        val isEmailMatches = Patterns.EMAIL_ADDRESS.matcher(
            binding.mainBlock.emailField.text.toString()
        ).matches()
        val isPasswordMatches = binding.mainBlock.passwordField.text.toString().isNotEmpty()
        binding.mainBlock.authButton.isEnabled = isEmailMatches && isPasswordMatches
    }

}