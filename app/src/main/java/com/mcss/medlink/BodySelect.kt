package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityBodySelectBinding

class BodySelect : AppCompatActivity() {

    lateinit var binding : ActivityBodySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.frontBody.setOnClickListener {
            val intent = Intent(this@BodySelect, Body::class.java)
            intent.putExtra("body_part", "front")
            startActivity(intent)
        }

//        binding.backBody.setOnClickListener {
//            val intent = Intent(this@BodySelect, Body::class.java)
//            intent.putExtra("body_part", "back")
//            startActivity(intent)
//        }
//
//        binding.leftBody.setOnClickListener {
//            val intent = Intent(this@BodySelect, Body::class.java)
//            intent.putExtra("body_part", "left")
//            startActivity(intent)
//        }
//
//        binding.rightBody.setOnClickListener {
//            val intent = Intent(this@BodySelect, Body::class.java)
//            intent.putExtra("body_part", "right")
//            startActivity(intent)
//        }

    }
}