package com.example.integradorandroidbored.Model

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.integradorandroidbored.Interfaces.Apiservice
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityActiviesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.log

class ActivityActivies : AppCompatActivity() {
    private lateinit var binding: ActivityActiviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActiviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayOf("Education", "Recreational", "Social", "Diy", "Charity", "Cooking", "Relaxation", "Music", "Busywork")
        val arrayAdapter = ArrayAdapter(this, R.layout.item, list)

        val numberarticipants = intent.getStringExtra("participants")

        binding.listView.adapter = arrayAdapter

        binding.listView.setOnItemClickListener{ _, _, position, _ ->
            val element = arrayAdapter.getItem(position)
            searchByParticipants(numberarticipants.toString(),element.toString().lowercase())
            val intent = Intent(this,SuggestionPage::class.java)
            intent.putExtra("Activity",element)
            startActivity(intent)
        }

        binding.imgRandom.setOnClickListener {
            searchByParticipants(numberarticipants.toString(),"")
            val intentRandom = Intent(this,SuggestionPage::class.java)
            intentRandom.putExtra("Activity","Random")
            startActivity(intentRandom)
        }

    }

    //con corrutinas
    private fun searchByParticipants(participants: String,category: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = if (category.isNullOrEmpty())
                Connection.getApiConexion().create(Apiservice::class.java).getActivitiesByParticipants(participants)
            else
                Connection.getApiConexion().create(Apiservice::class.java).getActivitiesByParticipantsAndType(category,participants)
            val activities = call.body()
            runOnUiThread {
                if (call.isSuccessful)
                    if (activities != null) {
                        Log.i("TAG",activities.activity.toString())
                        Log.i("TAG",activities.price.toString())
                        Log.i("TAG",activities.participants.toString())
                        Log.i("TAG",activities.type.toString())

                        Toast.makeText(this@ActivityActivies,activities.participants.toString(), Toast.LENGTH_LONG).show()
                    }
                    else
                        println("error")
            }
        }
    }

}