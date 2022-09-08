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
            if (check && binding.checkBox.isChecked) {
                val numberParticipants: String = binding.participantsAmount.text.toString()
                val intentActivies = Intent(this,ActivityActivies::class.java).apply { putExtra("participants",numberParticipants) }
                startActivity(intentActivies)
            }
            setMessageError(check)
        }
        binding.txtTerms.setOnClickListener{binding.layoutTerms.root.visibility = View.VISIBLE}
        binding.layoutTerms.imgClose.setOnClickListener{binding.layoutTerms.root.visibility = View.GONE}
    }

    private fun checkAmountParticipant() : Boolean {
        val participants = binding.participantsAmount.text
        return participants.isBlank() || participants.contains(regex)
    }

    private fun setMessageError(option: Boolean) {
        binding.errorMessage.visibility =
            if (option) View.INVISIBLE else View.VISIBLE
    }
}


