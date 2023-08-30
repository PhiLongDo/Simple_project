package com.dplong.simple_project.ui.main.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dplong.simple_project.databinding.FragmentSecondBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    private var _binding: FragmentSecondBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]
        bindingView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        _binding!!.content.setContent {
            SecondContent(vm = viewModel)
        }
        return _binding?.root
    }

    private fun bindingView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        SecondViewModel.Event.NavToFirstScreen -> {
                            val direction =
                                SecondFragmentDirections.actionSecondFragmentToFirstFragment()
                            findNavController().navigate(direction)
                        }
                        SecondViewModel.Event.Back -> {
                            findNavController().navigateUp()
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