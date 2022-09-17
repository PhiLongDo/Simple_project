package com.dplong.simple_project.ui.main.first

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dplong.simple_project.R
import com.dplong.simple_project.databinding.FragmentFirstBinding
import com.dplong.simple_project.databinding.FragmentLoginBinding

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}