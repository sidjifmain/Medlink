package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.mcss.medlink.databinding.ActivityAddPhotoBinding

class AddPhoto : AppCompatActivity() {
    lateinit var binding : ActivityAddPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        Status ve navigation color settings
        val newStatusBarColor = ContextCompat.getColor(this, R.color.primary_color)
        window.statusBarColor = newStatusBarColor
        window.navigationBarColor = newStatusBarColor

        binding.bottomNavigationView.selectedItemId = R.id.add

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.profile -> {
                    val user = FirebaseAuth.getInstance().currentUser

                    if (user != null) {
                        val intent = Intent(this, MyProfile::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, Welcome::class.java)
                        startActivity(intent)
                    }
                }
                R.id.home -> startActivity(Intent(this@AddPhoto , HomePage::class.java))

                else ->{



                }

            }

            true

        }


    }
}