package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mcss.medlink.databinding.ActivityDoctorProfileBinding

class DoctorProfile : AppCompatActivity() {

    lateinit var binding : ActivityDoctorProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val xestelik = intent.getStringExtra("apendesit")

        val doctorName = intent.getStringExtra("doctorName")

        Log.d("name",doctorName.toString())


        binding.doctorProfileImage.setImageResource(R.drawable.miri)
        binding.doctorProfileName.text = "Dr.Miryusif Babayev "
        binding.doctorProfileWork.text = "Baku Prime Hospital"
        binding.doctorProfileLocation.text = "Bakı, Ərdəbil küç. 45"
        binding.doctorProfileSahe.text = "neuropatoloq"
        binding.doctorProfileThumbnail.setImageResource(R.drawable.miri_thumbnail)
        binding.doctorProfileMelumat.text = "Miriyusif Babayev studied at Cerrahpaşa University Faculty of Medicine in Turkey. Currently working at Baku Prime Hospital"


        if (xestelik.toString() == "true"){
            binding.doctorProfileImage.setImageResource(R.drawable.rena)
            binding.doctorProfileName.text = "Dr.Rena Bayramli "
            binding.doctorProfileThumbnail.setImageResource(R.drawable.rena_thumbnail)
            binding.doctorProfileWork.text = "Baku Medical Plaza"
            binding.doctorProfileLocation.text = "42N Babak Pros, Baku 1142"
            binding.doctorProfileSahe.text = "Pediatrician"
            binding.doctorProfileMelumat.text = "Specialist doctor Rana Bayramli studied at Marmara University EAH Department of Child Health and Diseases. Currently working in Medilux clinic, Baku Medical plaza"

        }

        if (doctorName.toString() == "Serxan"){
            binding.videos.visibility = View.INVISIBLE
            binding.playButton.visibility = View.INVISIBLE
            binding.doctorProfileThumbnail.visibility = View.INVISIBLE
            binding.doctorProfileImage.setImageResource(R.drawable.serxan)
            binding.doctorProfileName.text = "Dr.Sarxan Khalıqov "
            binding.doctorProfileWork.text = "Baku Funda Hospital"
            binding.doctorProfileLocation.text = "Nərimanov r. İ.Hidayətzadə küç. 163, 1089 Arif Heydərov,"
            binding.doctorProfileSahe.text = "Psychologist"
            binding.doctorProfileMelumat.text="Sarxan Khalıqov studied at Boğaziçi University Faculty of Medicine. He is currently working at Funda Hospital."

        }
        if (doctorName.toString() == "Rena"){
            binding.doctorProfileImage.setImageResource(R.drawable.rena)
            binding.doctorProfileName.text = "Dr.Rena Bayramli "
            binding.doctorProfileThumbnail.setImageResource(R.drawable.rena_thumbnail)
            binding.doctorProfileWork.text = "Baku Medical Plaza"
            binding.doctorProfileLocation.text = "42N Babak Pros, Baku 1142"
            binding.doctorProfileSahe.text = "Pediatrician"
            binding.doctorProfileMelumat.text = "Specialist doctor Rana Bayramli studied at Marmara University EAH Department of Child Health and Diseases. Currently working in Medilux clinic, Baku Medical plaza"
        }




        binding.doctorProfileBook.setOnClickListener{
            startActivity(Intent(this@DoctorProfile , RandevuAl::class.java))
        }

        binding.playButton.setOnClickListener {

            val intent = Intent(this@DoctorProfile, PlayVideo::class.java)
            intent.putExtra("apendesit", xestelik)
            intent.putExtra("doctorName", doctorName.toString())
            startActivity(intent)
        }


    }
}