package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityWelcomeBinding

@Suppress("DEPRECATION")
class Welcome : AppCompatActivity() {
    lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.welcomeLoginBtn.setOnClickListener{
            startActivity(Intent(this@Welcome , LoginPage::class.java))
            finish()
        }

        binding.welcomeRegisterBtn.setOnClickListener{
            startActivity(Intent(this@Welcome , Register::class.java))
            finish()
        }

        binding.welcomeBackBtn.setOnClickListener{
            onBackPressed()
        }


    }
}