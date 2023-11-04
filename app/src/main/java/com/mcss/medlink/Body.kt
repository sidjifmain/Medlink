package com.mcss.medlink

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mcss.medlink.databinding.ActivityBodyBinding

class Body : AppCompatActivity() {
    lateinit var binding : ActivityBodyBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val bodyPart = intent.getStringExtra("body_part")

        when (bodyPart) {
            "front" -> {
                binding.bodyImage.setImageResource(R.drawable.front_body)
            }
            "back" -> {
                binding.bodyImage.setImageResource(R.drawable.back_body)
            }
            "left" -> {
                binding.bodyImage.setImageResource(R.drawable.left_body)
            }
            "right" -> {
                binding.bodyImage.setImageResource(R.drawable.right_body)
            }
            else -> {

            }
        }


    }
}