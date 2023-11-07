package com.mcss.medlink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import com.mcss.medlink.databinding.ActivityDoctorsBinding
import com.mcss.medlink.databinding.ActivityPlayVideoBinding

class PlayVideo : AppCompatActivity() {
    lateinit var binding : ActivityPlayVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctorname = intent.getStringExtra("doctorName")
        val apendesit = intent.getStringExtra("apendesit")
        val xesteVideo = intent.getStringExtra("xestevideo")




        binding.playVideoName.text = "Dr.${doctorname.toString()}"
        binding.circleImageView.setImageResource(R.drawable.miri)

        var videoPath = "android.resource://${packageName}/${R.raw.miri_tanitim}"
        var videoUri = Uri.parse(videoPath)

        if (xesteVideo.toString() == "true"){
            videoPath = "android.resource://${packageName}/${R.raw.qarinagrisi}"
            binding.circleImageView.setImageResource(R.drawable.rena)
            binding.playVideoName.text = "Dr.Rean Bayramli"
            videoUri = Uri.parse(videoPath)

        }



        if (apendesit.toString() == "true"){
            videoPath = "android.resource://${packageName}/${R.raw.rena_tanitim}"
            binding.circleImageView.setImageResource(R.drawable.rena)
            binding.playVideoName.text = "Dr.${doctorname.toString()}"
            videoUri = Uri.parse(videoPath)

        }

        if (doctorname.toString() == "Rena"){
             videoPath = "android.resource://${packageName}/${R.raw.rena_tanitim}"
            binding.circleImageView.setImageResource(R.drawable.rena)
            binding.playVideoName.text = "Dr.${doctorname.toString()}"
            videoUri = Uri.parse(videoPath)

        }

        




        binding.videoView.setVideoURI(videoUri)
        binding.videoView.start()

        var isPlaying = true

        val pauseImageView = binding.pauseBtn

        binding.videoView.setOnClickListener {
            if (isPlaying) {
                binding.videoView.pause()
                pauseImageView.visibility = View.VISIBLE
            } else {
                binding.videoView.start()
                pauseImageView.visibility = View.INVISIBLE
            }
            isPlaying = !isPlaying
        }


    }
        override fun onPause() {
            super.onPause()
            binding.videoView.stopPlayback()
        }
}