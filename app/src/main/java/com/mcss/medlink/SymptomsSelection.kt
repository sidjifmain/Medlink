package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivitySymptomsSelectionBinding

class SymptomsSelection : AppCompatActivity() {

    lateinit var binding : ActivitySymptomsSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySymptomsSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chkc1.isChecked = true
        binding.chck2.isChecked = true
        binding.chck3.isChecked = true
        binding.chck4.isChecked = true


        binding.sSelectNext.setOnClickListener{
            startActivity(Intent(this@SymptomsSelection , SymptomCheck::class.java))
        }


    }
}