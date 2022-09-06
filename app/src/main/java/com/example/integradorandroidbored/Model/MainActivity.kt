package com.example.integradorandroidbored.Model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.integradorandroidbored.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainButton.setOnClickListener {
            startActivity(Intent(this, ActivityActivies::class.java))
        }

        binding.txtTerms.setOnClickListener{binding.layoutTerms.root.visibility = View.VISIBLE}
        binding.layoutTerms.imgClose.setOnClickListener{binding.layoutTerms.root.visibility = View.GONE}
    }
}


