package com.example.integradorandroidbored.Model

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.integradorandroidbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val regex = "^([1-8])$".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainButton.setOnClickListener {
            val check = checkAmountParticipant()
            if (check) { startActivity(Intent(this, ActivityActivies::class.java)) }
            setMessageError(check)
        }

        binding.txtTerms.setOnClickListener{binding.layoutTerms.root.visibility = View.VISIBLE}
        binding.layoutTerms.imgClose.setOnClickListener{binding.layoutTerms.root.visibility = View.GONE}
    }

    private fun checkAmountParticipant() : Boolean {
        val participants = binding.participantsAmount.text
        return participants.isNotBlank() && participants.contains(regex)
    }

    private fun setMessageError(option: Boolean) {
        val currentVisibility = binding.errorMessage.visibility
        binding.errorMessage.visibility =
            if (option && currentVisibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }
}


