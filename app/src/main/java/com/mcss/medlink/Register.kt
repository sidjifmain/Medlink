package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mcss.medlink.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        binding.registerSignUpBtn.setOnClickListener {
            val email = binding.registerEmailInput.text.toString()
            val pass = binding.registerPasswordInput.text.toString()
            val age = binding.registerAgeInput.text.toString()
            val name = binding.registerNameInput.text.toString()

            val checkBoxAccepted = binding.registerQanun.isChecked
            val isMale = binding.registerMale.isChecked

            if (email.isNotEmpty() && pass.isNotEmpty() && age.isNotEmpty() && name.isNotEmpty()) {
                if (checkBoxAccepted) {
                    binding.phoneProgressBar.visibility = View.VISIBLE
                    binding.registerSignUpBtn.isEnabled = false

                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                            binding.phoneProgressBar.visibility = View.GONE
                            binding.registerSignUpBtn.isEnabled = true

                            if (task.isSuccessful) {
                                val user = firebaseAuth.currentUser
                                val userId = user?.uid

                                val userMap = hashMapOf(
                                    "name" to name,
                                    "age" to age,
                                    "email" to email,
                                    "sex" to if (isMale) "male" else "female",
                                    "doctor" to "false",
                                    "sahe" to ""
                                )

                                if (userId != null) {
                                    val usersRef = db.collection("users")
                                    usersRef.document(userId)
                                        .set(userMap)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this@Register, HomePage::class.java))
                                            finish()
                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("Firestore Error", "Firestore Error: $e")
                                        }
                                }
                            } else {
                                Log.e("Authentication Error", "Auth error: ${task.exception}")
                                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                            }
                        })
                } else {
                    Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Check Box Aktiv Passiv
        binding.registerMale.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.registerFemale.isChecked = false
            }
        }

        binding.registerFemale.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.registerMale.isChecked = false
            }
        }
    }
}
