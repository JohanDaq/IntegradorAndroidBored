package com.example.integradorandroidbored.Model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.integradorandroidbored.R
import com.example.integradorandroidbored.databinding.ActivityActiviesBinding


class ActivityActivies : AppCompatActivity() {
    private lateinit var binding: ActivityActiviesBinding
    private lateinit var participants: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActiviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayAdapter = ArrayAdapter(this, R.layout.item, resources.getStringArray(R.array.categories))

        intent.extras?.run { participants = getString("participants").toString() }
        binding.listView.adapter = arrayAdapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val element = arrayAdapter.getItem(position)
            goActivity(
                this@ActivityActivies,
                SuggestionPage::class.java,
                element.toString().lowercase(),
                participants
            )
        }
        binding.imgRandom.setOnClickListener {
            goActivity(
                this@ActivityActivies,
                SuggestionPage::class.java,
                "",
                participants
            )
        }
    }

    private fun goActivity(
        activity: Activity,
        cls: Class<*>?,
        tyeActivity: String? = null,
        participants: String
    ) {
        val intent = Intent()
        intent.setClass(activity, cls!!).apply {
            putExtra("tyeActivity", tyeActivity)
            putExtra("participants", participants)
        }
        activity.startActivity(intent)
    }
}