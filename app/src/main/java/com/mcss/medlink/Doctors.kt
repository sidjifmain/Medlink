package com.mcss.medlink

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.mcss.medlink.Adapters.DoctorAdapter
import com.mcss.medlink.dataClasses.DoctorData
import com.mcss.medlink.databinding.ActivityDoctorsBinding
import com.mcss.medlink.databinding.FilterWindowBinding
import java.util.Locale

@Suppress("DEPRECATION")
class Doctors : AppCompatActivity() {
    lateinit var binding : ActivityDoctorsBinding
    private var mList = ArrayList<DoctorData>()
    private lateinit var adapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      RecylerView ucun settingler
        binding.doctorRecylerView.setHasFixedSize(true)
        binding.doctorRecylerView.layoutManager = LinearLayoutManager(this)
        adapter = DoctorAdapter(mList)
        binding.doctorRecylerView.adapter = adapter

        fetchDoctorDataFromFirestore(
            onSuccess = { doctorDataList ->
                mList.clear() // Önce mevcut verileri temizleyin
                mList.addAll(doctorDataList) // Firestore'dan gelen verileri ekleyin
                Log.d("sala",mList.size.toString())
                adapter.notifyDataSetChanged() // RecyclerView'i güncelleyin
            },
            onFailure = { exception ->
                Log.w("Firestore Error", "Error getting documents: $exception")
            }
        )








        binding.doctorSearcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })


//        Back button
        binding.doctorBackButton.setOnClickListener{
            onBackPressed()
        }

//       Filter Button
        binding.doctorsFilterButton.setOnClickListener{
            filterPopup(this@Doctors)

        }













    }


    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<DoctorData>()
            for (i in mList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun fetchDoctorDataFromFirestore(onSuccess: (List<DoctorData>) -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val userList = mutableListOf<DoctorData>()

        db.collection("users")
            .whereEqualTo("doctor", "true")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("name")
                    val sahe = document.getString("sahe")

                    if (name != null && sahe != null) {
                        userList.add(DoctorData(name.toString(), "Men yaxsi oglanam", logo = R.drawable.doctor, sahe = sahe.toString()))
                    }
                }
                onSuccess(userList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }


    fun filterPopup(context: Context,) {
        val popupView = LayoutInflater.from(context).inflate(R.layout.filter_window, null)
        val popupBinding = FilterWindowBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView,
            900,
            600
        )

        popupWindow.isFocusable = true

        popupWindow.isOutsideTouchable = true

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, -550)

        popupBinding.filterApplyButton.setOnClickListener {
            val isCardiologistSelected = popupBinding.cardiologist.isChecked
            val isNeurologistSelected = popupBinding.neurologist.isChecked
            val isPsychiatristSelected = popupBinding.psychiatrist.isChecked
            val isSurgeonSelected = popupBinding.surgeon.isChecked

            val filteredList = mutableListOf<DoctorData>()

            if (isCardiologistSelected || isNeurologistSelected || isPsychiatristSelected || isSurgeonSelected) {
                for (doctorData in mList) {
                    if ((isCardiologistSelected && doctorData.sahe == "cardiologist") ||
                        (isNeurologistSelected && doctorData.sahe == "neurologist") ||
                        (isPsychiatristSelected && doctorData.sahe == "psychiatrist") ||
                        (isSurgeonSelected && doctorData.sahe == "surgeon")
                    ) {
                        filteredList.add(doctorData)
                    }
                }
            } else {
                filteredList.addAll(mList)
            }

            adapter.updateData(filteredList)
            popupWindow.dismiss()
        }


    }
}