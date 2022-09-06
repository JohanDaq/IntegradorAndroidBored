package com.example.integradorandroidbored.Model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityMainBinding
import com.example.integradorandroidbored.databinding.ActivitySuggestionPageBinding

class SuggestionPage : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("Activity")
        binding.txtToolbar.setText(title.toString())

        binding.imgClose.setOnClickListener { finish() }
    }
}