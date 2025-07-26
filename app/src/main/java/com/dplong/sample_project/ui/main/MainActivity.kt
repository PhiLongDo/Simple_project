package com.dplong.sample_project.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dplong.sample_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        email = intent.getStringExtra("email")
        val view = binding.root
        setContentView(view)
    }
}