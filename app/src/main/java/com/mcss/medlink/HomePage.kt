package com.mcss.medlink

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mcss.medlink.databinding.ActivityHomePageBinding
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService

class HomePage : AppCompatActivity() {
    lateinit var binding : ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)





//        Firebase Referanslari

        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()
        val collectionReference = FirebaseFirestore.getInstance().collection("users")



//        Home pagede adi deyistirmek ucun

        if (user != null) {
            val uuid = user.uid
            val collectionReference = FirebaseFirestore.getInstance().collection("users")
            val documentReference = collectionReference.document(uuid)

            videoCallServices(user.email.toString())
            Log.d("videoCallREgister","oldu")

            if (user!!.email.toString() == "sidjifmain@gmail.com" ){
                Log.d("salam","oldu")
                binding.homePpPhoto.setImageResource(R.drawable.mehdi)
            }
            else if (user!!.email.toString() == "miryusifbabayev42@gmail.com" ){
                Log.d("salam","oldu")
                binding.homePpPhoto.setImageResource(R.drawable.miri)
            }
            else {
                binding.homePpPhoto.setImageResource(R.drawable.pp)
            }

            documentReference.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        if (userData != null && userData["name"] is String) {
                            val ad = userData["name"] as String
                            binding.homePageName.text = "Hi, $ad"
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




//        Status ve navigation color settings
        val newStatusBarColor = ContextCompat.getColor(this, R.color.primary_color)
        window.statusBarColor = newStatusBarColor
        window.navigationBarColor = newStatusBarColor



        binding.sympthomChecker.setOnClickListener{
            startActivity(Intent(this@HomePage , BodySelect::class.java))
        }

        binding.doctors.setOnClickListener{
            startActivity(Intent(this@HomePage , Doctors::class.java))
        }

        binding.homePpPhoto.setOnClickListener{
            val user = FirebaseAuth.getInstance().currentUser

            if (user != null) {
                val intent = Intent(this, MyProfile::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
            }


        }

        binding.drugReminder.setOnClickListener{
            startActivity(Intent(this@HomePage , DrugReminder::class.java))

        }

        binding.meetings.setOnClickListener{
            startActivity(Intent(this@HomePage , Meetings::class.java))

        }

        binding.bottomNavigationView.selectedItemId = R.id.home

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
                R.id.add -> startActivity(Intent(this@HomePage , AddPhoto::class.java))

                else ->{



                }

            }

            true

        }




    }

    private fun videoCallServices(userID: String) {
        val appID: Long = 1473626691
        val appSign = "4cabe97666845a863bea122df5fc88aac52301a3aa487f168ac9697e857725ec"
        val application = application
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true
        val notificationConfig = ZegoNotificationConfig()
        notificationConfig.sound = "zego_uikit_sound_call"
        notificationConfig.channelID = "CallInvitation"
        notificationConfig.channelName = "CallInvitation"
        ZegoUIKitPrebuiltCallInvitationService.init(
            application,
            appID,
            appSign,
            userID,
            userID,
            callInvitationConfig
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }
}