package com.dplong.simple_project.ui.login

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dplong.simple_project.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun adjustFontScale(configuration: Configuration) {
        if (configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f
            val metrics = resources.displayMetrics
            metrics.scaledDensity = configuration.fontScale * configuration.densityDpi
            baseContext.createConfigurationContext(configuration)
            baseContext.resources.displayMetrics.setTo(metrics)
        }
    }
}