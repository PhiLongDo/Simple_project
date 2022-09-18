package com.dplong.simple_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dplong.simple_project.databinding.ActivityMainBinding
import com.dplong.simple_project.ui.main.first.FirstFragment

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