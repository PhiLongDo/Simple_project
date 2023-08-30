package com.dplong.simple_project.ui.main.first

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dplong.simple_project.data.pref.AppPreferences
import com.dplong.simple_project.databinding.FragmentFirstBinding
import com.dplong.simple_project.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    private var _binding: FragmentFirstBinding? = null

    @Inject
    lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        bindingView()
        Log.d("Email", preferences.username)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val email = (requireActivity() as MainActivity).email
        viewModel.sentEmail(email ?: "")
        _binding!!.content.setContent {
            FirstContent(vm = viewModel)
        }

        return _binding?.root
    }

    private fun bindingView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        FirstViewModel.Event.NavToSecondScreen -> {
                            val direction =
                                FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                            findNavController().navigate(direction)
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