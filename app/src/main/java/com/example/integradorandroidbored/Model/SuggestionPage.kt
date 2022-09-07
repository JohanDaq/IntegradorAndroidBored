package com.example.integradorandroidbored.Model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.telecom.Call
import android.widget.Toast
import com.example.integradorandroidbored.Interfaces.Apiservice
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityMainBinding
import com.example.integradorandroidbored.databinding.ActivitySuggestionPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestionPage : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("Activity")
        binding.txtToolbar.setText(title.toString())

        binding.imgClose.setOnClickListener { finish() }

        if(title.toString() == "Random"){
            binding.lblCategory.visibility = View.VISIBLE
        }

        val price = 0.6
        val textPrice: String = when{
            price == 0.0 -> getString(R.string.free_price)
            price < 0.3 -> getString(R.string.low_price)
            0.3 <= price && price < 0.6 -> getString(R.string.med_price)
            price >= 0.6 -> getString(R.string.high_price)
            else -> "Priceless"
        }

        binding.lblPrice.setText(textPrice)

    }
}