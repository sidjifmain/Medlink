package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mcss.medlink.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.button.setOnClickListener {
            val email = binding.loginEmailInput.text.toString()
            val pass = binding.loginPasswordInput.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                binding.phoneProgressBar.visibility = View.VISIBLE
                binding.button.isEnabled = false

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    binding.phoneProgressBar.visibility = View.GONE
                    binding.button.isEnabled = true

                    if (it.isSuccessful) {
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}