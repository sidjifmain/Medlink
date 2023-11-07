package com.mcss.medlink

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityXestelikBinding

class Xestelik : AppCompatActivity() {
    lateinit var binding: ActivityXestelikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXestelikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.xestelikVideo.setOnClickListener{
            intent = Intent(this@Xestelik , PlayVideo::class.java)
            intent.putExtra("xestevideo", "true")

            startActivity(intent)
        }
        binding.xestelikPlayButton.setOnClickListener{
            intent = Intent(this@Xestelik , PlayVideo::class.java)
            intent.putExtra("xestevideo", "true")


            startActivity(intent)
        }

        binding.xestelikprofile.setOnClickListener{
            val intent = Intent(this@Xestelik, DoctorProfile::class.java)
            intent.putExtra("apendesit", "true")
            startActivity(intent) // Intent'i başlatın
        }









    }
}