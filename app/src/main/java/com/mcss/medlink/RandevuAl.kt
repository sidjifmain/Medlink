package com.mcss.medlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcss.medlink.databinding.ActivityRandevuAlBinding
import java.util.Calendar

class RandevuAl : AppCompatActivity() {
    lateinit var binding : ActivityRandevuAlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandevuAlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sistemTarihi = getSistemTarihi()

        binding.dateText.text = sistemTarihi

        val sistemSaati = getSistemSaati()
        val sistemDakikasi = getSistemDakikasi()

        val saatMetin = if (sistemSaati < 10) "0$sistemSaati" else sistemSaati.toString()
        val dakikaMetin = if (sistemDakikasi < 10) "0$sistemDakikasi" else sistemDakikasi.toString()

        binding.timeInterval.text = "$saatMetin:$dakikaMetin"





        binding.minute5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.minute10.isChecked = false
                binding.minute15.isChecked = false
                updateSelectedTimeText(5)
            }
        }

        binding.minute10.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.minute5.isChecked = false
                binding.minute15.isChecked = false
                updateSelectedTimeText(10)
            }
        }

        binding.minute15.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.minute10.isChecked = false
                binding.minute5.isChecked = false
                updateSelectedTimeText(15)
            }
        }

        binding.reandevualNext.setOnClickListener{
            startActivity(Intent(this@RandevuAl , Meetings::class.java))
        }



    }



    fun getSistemTarihi(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val tarih = "$day/$month/$year"

        return tarih
    }

    fun getSistemSaati(): Int {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return hour
    }

    fun getSistemDakikasi(): Int {
        val calendar = Calendar.getInstance()
        val minute = calendar.get(Calendar.MINUTE)
        return minute
    }

    fun updateSelectedTimeText(selectedMinutes: Int) {
        val sistemSaati = getSistemSaati()
        val sistemDakikasi = getSistemDakikasi()

        val bitisDakikasi = (sistemDakikasi + selectedMinutes) % 60
        val saatMetin = if (sistemSaati < 10) "0$sistemSaati" else sistemSaati.toString()
        val dakikaMetin = if (sistemDakikasi < 10) "0$sistemDakikasi" else sistemDakikasi.toString()
        val bitisSaatMetin = if (bitisDakikasi == 0) {
            if ((sistemSaati + (sistemDakikasi + selectedMinutes) / 60) < 10) {
                "0${sistemSaati + (sistemDakikasi + selectedMinutes) / 60}"
            } else {
                (sistemSaati + (sistemDakikasi + selectedMinutes) / 60).toString()
            }
        } else {
            if ((sistemSaati + (sistemDakikasi + selectedMinutes) / 60) < 10) {
                "0${sistemSaati + (sistemDakikasi + selectedMinutes) / 60}"
            } else {
                (sistemSaati + (sistemDakikasi + selectedMinutes) / 60).toString()
            }
        }
        val bitisDakikaMetin = if (bitisDakikasi == 0) "00" else if (bitisDakikasi < 10) "0$bitisDakikasi" else bitisDakikasi.toString()

        binding.timeInterval.text = "$saatMetin:$dakikaMetin - $bitisSaatMetin:$bitisDakikaMetin"
    }









}