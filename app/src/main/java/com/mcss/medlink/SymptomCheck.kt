package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivitySymptomCheckBinding

class SymptomCheck : AppCompatActivity() {
    lateinit var binding : ActivitySymptomCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySymptomCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chckBad.isChecked = true
        binding.chckD.isChecked = true
        binding.chckHour.isChecked = true
        binding.chckprsesnt.isChecked = true
        binding.chckIshal.isChecked = true

        binding.cSelectNext.setOnClickListener{
            startActivity(Intent(this@SymptomCheck , Xestelikler::class.java))
        }


    }
}