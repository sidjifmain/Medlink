package com.mcss.medlink

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mcss.medlink.databinding.ActivityMeetingsBinding
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class Meetings : AppCompatActivity() {

    lateinit var binding : ActivityMeetingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            Log.d("salam" , "visible")
            binding.imageView19.visibility = View.VISIBLE
            binding.circleImageView2.visibility = View.VISIBLE
            binding.name.visibility = View.VISIBLE
            binding.meetButton.visibility = View.VISIBLE
            binding.now.visibility = View.VISIBLE
            binding.time.visibility = View.VISIBLE

            if(user.email.toString() == "miryusifbabayev42@gmail.com"){
                binding.circleImageView2.setImageResource(R.drawable.mehdi)
                binding.name.text = "Mehdi Israfilov"
            }

        } else {
            binding.notmeet.visibility = View.VISIBLE
        }


        binding.meetButton.setOnClickListener{
            startActivity(Intent(this@Meetings , VideoCall::class.java))
        }


    }
}