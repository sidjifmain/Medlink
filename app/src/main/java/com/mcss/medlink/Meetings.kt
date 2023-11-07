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
import com.mcss.medlink.databinding.ActivityMeetingsBinding
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class Meetings : AppCompatActivity() {

    lateinit var binding : ActivityMeetingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.meetButton.setOnClickListener{
            startActivity(Intent(this@Meetings , VideoCall::class.java))
        }


    }
}