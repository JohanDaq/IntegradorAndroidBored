package com.example.integradorandroidbored.Model

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityActiviesBinding

class ActivityActivies : AppCompatActivity() {
    private lateinit var binding: ActivityActiviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActiviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayOf("Education", "Recreational", "Social", "Diy", "Charity", "Cooking", "Relaxation", "Music", "Busywork")
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item, list)

        binding.listView.adapter = arrayAdapter
        binding.listView.setOnItemClickListener{ parent, view, position, id ->
            val element = arrayAdapter.getItem(position)
            val intent = Intent(this,SuggestionPage::class.java)
            intent.putExtra("Activity",element)
            startActivity(intent)
        }

        binding.imgRandom.setOnClickListener {
            val intentRandom = Intent(this,SuggestionPage::class.java)
            intentRandom.putExtra("Activity","Random")
            startActivity(intentRandom)
        }

    }
}