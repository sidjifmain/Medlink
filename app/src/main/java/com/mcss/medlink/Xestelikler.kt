package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Xestelikler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xestelikler)

        val more = findViewById<Button>(R.id.more)

        more.setOnClickListener{
            startActivity(Intent(this@Xestelikler , Xestelik::class.java))
        }
    }
}