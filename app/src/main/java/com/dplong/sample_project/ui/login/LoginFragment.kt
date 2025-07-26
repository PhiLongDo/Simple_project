package com.dplong.sample_project.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dplong.sample_project.databinding.FragmentLoginBinding
import com.dplong.sample_project.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        _binding!!.content.setContent {
            LoginContent(viewModel)
        }
        return _binding?.root
    }

    private fun bindingView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        LoginViewModel.LoginEvent.Login -> {
                            val intent = Intent(
                                requireContext(),
                                MainActivity::class.java
                            )
                            intent.putExtra("email", viewModel.state.email)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}