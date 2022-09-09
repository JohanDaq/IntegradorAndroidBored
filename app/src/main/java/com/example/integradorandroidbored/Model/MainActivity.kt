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
                val intentActivities = Intent(this,ActivityActivities::class.java).apply { putExtra("participants",numberParticipants) }
                startActivity(intentActivities)
            }
            setMessageError(check)
        }
        binding.txtTerms.setOnClickListener{binding.layoutTerms.root.visibility = View.VISIBLE}
        binding.layoutTerms.imgClose.setOnClickListener{binding.layoutTerms.root.visibility = View.GONE}
    }

    /**
     * Validates that the participants number is in range 1 to 8 or it is empty
     * @return [Boolean]
     * @author Aponte, Pineda & Tolaba
     */
    private fun checkAmountParticipant() : Boolean {
        val participants = binding.participantsAmount.text
        return participants.isBlank() || participants.contains(regex)
    }

    /**
     * Shows a error message when the participants condition is false
     * @param option [Boolean] participants condition
     * @author Aponte, Pineda & Tolaba
     */
    private fun setMessageError(option: Boolean) {
        binding.errorMessage.visibility =
            if (option) View.INVISIBLE else View.VISIBLE
    }
}


