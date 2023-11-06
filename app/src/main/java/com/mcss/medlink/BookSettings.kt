package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityBookSettingsBinding

class BookSettings : AppCompatActivity() {
    lateinit var binding : ActivityBookSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.book2.setOnClickListener{
            startActivity(Intent(this@BookSettings , Meetings::class.java))
        }




    }
}