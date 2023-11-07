package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityQarinBinding

class Qarin : AppCompatActivity() {
    lateinit var binding : ActivityQarinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQarinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.altQarin.setOnClickListener{
            startActivity(Intent(this@Qarin , SymptomsSelection::class.java))
        }
    }
}