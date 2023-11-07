package com.mcss.medlink

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
import com.mcss.medlink.databinding.ActivityMeetingsBinding
import com.mcss.medlink.databinding.ActivityVideoCallBinding
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class VideoCall : AppCompatActivity() {

    lateinit var binding : ActivityVideoCallBinding

    private lateinit var receiverUserId: EditText
    private lateinit var videoCallBtn: ZegoSendCallInvitationButton
    private lateinit var audioCallBtn: ZegoSendCallInvitationButton
    private lateinit var buttonLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var targetUser = ""
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {


            if(user.email.toString() == "miryusifbabayev42@gmail.com"){
                targetUser = "sidjifmain@gmail.com"
            }
            else if(user.email.toString() == "sidjifmain@gmail.com"){
                targetUser = "miryusifbabayev42@gmail.com"
            }

        }


        receiverUserId = findViewById(R.id.receiver_user_id_text_field)
        videoCallBtn = findViewById(R.id.video_call_btn)
        audioCallBtn = findViewById(R.id.audio_call_btn)
        buttonLayout = findViewById(R.id.buttons_layout)



        receiverUserId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Burada bir işlem yapmanız gerekirse ekleyin
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val _receiverId = receiverUserId.text.toString()
                if (_receiverId.isNotEmpty()) {
//                    buttonLayout.visibility = View.VISIBLE
                    startVideoCall(_receiverId)
                    startAudioCall(_receiverId)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Burada bir işlem yapmanız gerekirse ekleyin
            }
        })

        receiverUserId.setText(targetUser)
        receiverUserId.postDelayed({
            if (receiverUserId.text.isNotEmpty()) {
//                buttonLayout.visibility = View.VISIBLE
                videoCallBtn.performClick()
                finish()
            }
        }, 1000)

    }

    private fun startVideoCall(receiverId: String) {
        binding.videoCallBtn.setIsVideoCall(true)
        binding.videoCallBtn.resourceID = "zego_uikit_call"
        binding.videoCallBtn.setInvitees(listOf(ZegoUIKitUser(receiverId)))
    }

    private fun startAudioCall(receiverId: String) {
        binding.audioCallBtn.setIsVideoCall(false)
        binding.audioCallBtn.resourceID = "zego_uikit_call"
        binding.audioCallBtn.setInvitees(listOf(ZegoUIKitUser(receiverId)))
    }




}