package com.example.integradorandroidbored.Model

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityActiviesBinding

class ActivityActivies : AppCompatActivity() {
    private lateinit var binding: ActivityActiviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activies)

        binding = ActivityActiviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayOf("Education", "Recreational", "Social", "Diy", "Charity", "Cooking", "Relaxation", "Music", "Busywork")
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item, list)

        binding.listView.adapter = arrayAdapter
        binding.listView.setOnItemClickListener{ parent, view, position, id ->
            val element = arrayAdapter.getItem(position)
            Toast.makeText(this, element, Toast.LENGTH_SHORT).show()
        }

    }
}