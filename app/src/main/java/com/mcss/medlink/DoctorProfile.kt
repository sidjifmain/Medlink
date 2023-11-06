package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityDoctorProfileBinding

class DoctorProfile : AppCompatActivity() {

    lateinit var binding : ActivityDoctorProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.doctorProfileBook.setOnClickListener{
            startActivity(Intent(this@DoctorProfile , BookSettings::class.java))
        }

        binding.playButton.setOnClickListener {
            startActivity(Intent(this@DoctorProfile , PlayVideo::class.java))
        }


    }
}