package com.example.integradorandroidbored.Model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import com.example.integradorandroidbored.Interfaces.Apiservice
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivitySuggestionPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashMap

class SuggestionPage : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionPageBinding
    private lateinit var category: String
    private lateinit var numberParticipants: String
    private var randomBoolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.run {
            category = getString("tyeActivity").toString()
            numberParticipants = getString("participants").toString()
            searchActivities(numberParticipants, category)
        }

        binding.imgClose.setOnClickListener { finish() }
        binding.mainButton.setOnClickListener { searchActivities(numberParticipants, category) }
    }

    /**
     * Coroutine that requests information from the API
     * @param participants [String] query the request by a numbers of participants
     * @param category [String] query the request by activity type
     * @author Aponte, Pineda & Tolaba
     */
    private fun searchActivities(participants: String, category: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Connection.getApiConnection().create(Apiservice::class.java)
                .getBoredResponse(getQueryMap(participants,category))
            val activities = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    activities?.let { showValidateData(it) }
                }else Toast.makeText(this@SuggestionPage,"An error has occurred",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Validates the content of the response [BoredResponse] then calls the corresponding function
     * @param response [BoredResponse] and object that contains information such as activity, participants & price
     * @author Aponte, Pineda & Tolaba
     */
    private fun showValidateData(activities: BoredResponse) {
        if (activities.activity.isNotBlank())
            showResponse(activities, randomBoolean)
        else
            showErrorMessage()
    }

    /**
     * Shows the labels with the response [BoredResponse] information at Suggest Activity screen
     * @param response [BoredResponse] and object that contains information such as activity, participants & price
     * @param random [Boolean] enables hidden information about category
     * @author Aponte, Pineda & Tolaba
     */
    private fun showResponse(response: BoredResponse, random: Boolean) {
        var activityTitle = response.type
        activityTitle = activityTitle.replaceFirstChar { it.uppercase() }

        if (random) {
            binding.lblCategory.visibility = View.VISIBLE
            binding.textCategory.text = response.type
            binding.txtToolbar.text = "Random"
        } else binding.txtToolbar.text = activityTitle


        binding.lblParticipants.text = response.participants.toString()
        binding.activityName.text = response.activity

        binding.lblPrice.text = getTextPrice(response.price.toDouble())
        binding.layoutContent.visibility = View.VISIBLE
        binding.message.visibility = GONE
    }

    /**
     * Shows a text message if the activity suggestion is null or empty
     * @author Aponte, Pineda & Tolaba
     */
    private fun showErrorMessage() {
        binding.txtToolbar.text = getString(R.string.activity)
        binding.layoutContent.visibility = GONE
        binding.message.visibility = View.VISIBLE
    }

    /**
     * Given a price [Double] it returns a label based on price range
     * @param price [Double] comparable range
     * @return [String] Indicator of price range
     * @author Aponte, Pineda & Tolaba
     */
    private fun getTextPrice(price: Double): String{
        return when {
            price == 0.0 -> getString(R.string.free_price)
            price < 0.3 -> getString(R.string.low_price)
            0.3 <= price && price < 0.6 -> getString(R.string.med_price)
            price >= 0.6 -> getString(R.string.high_price)
            else -> "No price"
        }
    }

    /**
     * Query the data based on a numbers of participants, category or both parameters
     * @param participants [String] number of people taking part in the activity
     * @param category [String] type of recreational activity
     * @return data [MutableMap]
     * @author Aponte, Pineda & Tolaba
     */
    private fun getQueryMap(participants: String,category: String): MutableMap<String,String>{
        val data: MutableMap<String, String> = HashMap()
        if (category.isNotBlank()) data["type"] = category else randomBoolean = true
        if (participants.isNotBlank()) data["participants"] = participants
        return data
    }
}