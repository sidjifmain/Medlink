package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mcss.medlink.databinding.ActivityMyProfileBinding

class MyProfile : AppCompatActivity() {
    lateinit var binding : ActivityMyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        Status ve navigation color settings
        val newStatusBarColor = ContextCompat.getColor(this, R.color.primary_color)
        window.statusBarColor = newStatusBarColor
        window.navigationBarColor = newStatusBarColor



        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()
        val collectionReference = FirebaseFirestore.getInstance().collection("users")









//        Home pagede adi deyistirmek ucun

        if (user != null) {
            val uuid = user.uid
            val collectionReference = FirebaseFirestore.getInstance().collection("users")
            val documentReference = collectionReference.document(uuid)

            if (user!!.email.toString() == "sidjifmain@gmail.com" ){
                Log.d("salam","oldu")
                binding.myProfileImage.setImageResource(R.drawable.mehdi)
            }
            else if (user!!.email.toString() == "miryusifbabayev42@gmail.com" ){
                Log.d("salam","oldu")
                binding.myProfileImage.setImageResource(R.drawable.miri)
            }
            else {
                binding.myProfileImage.setImageResource(R.drawable.pp)
            }

            documentReference.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        if (userData != null && userData["name"] is String) {
                            val ad = userData["name"] as String
                            binding.myProfileName.text = "Hi, $ad"
                        } else {
                        }
                    } else {
                    }
                }
                .addOnFailureListener { e ->
                }
        } else {
            Log.d("giris", "Kullanıcı oturum açmamış")
        }


        binding.myProfileLogoutNButton.setOnClickListener{
            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            startActivity(Intent(this@MyProfile , HomePage::class.java))
        }



//        navigation View ucun sekil
        binding.bottomNavigationView.selectedItemId = R.id.profile

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> startActivity(Intent(this@MyProfile , HomePage::class.java))
                R.id.add -> startActivity(Intent(this@MyProfile , AddPhoto::class.java))

                else ->{



                }

            }

            true

        }


    }
}