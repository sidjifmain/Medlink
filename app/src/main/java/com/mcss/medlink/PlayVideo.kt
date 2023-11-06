package com.mcss.medlink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val videoPath = "android.resource://${packageName}/${R.raw.rena_tanitim}"
        val videoUri = Uri.parse(videoPath)

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