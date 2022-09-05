package com.example.integradorandroidbored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.integradorandroidbored.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainButton.setOnClickListener{
            binding.mainText.setText("Hola desde acá")
        }
    }


}