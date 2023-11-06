package com.mcss.medlink


import android.content.Context
import android.content.Intent
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
import kotlin.math.log

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
                mList.clear()
                mList.addAll(doctorDataList)
                Log.d("sala",mList.size.toString())
                adapter.notifyDataSetChanged()
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
        val doctorDataMap = HashMap<String, DoctorData>()

        db.collection("users")
            .whereEqualTo("doctor", "true")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("name")
                    val sahe = document.getString("sahe")
                    val email = document.getString("email")

                    if (name != null && sahe != null && email != null) {
                        val doctorData = when (email) {
                            "miryusifbabayev42@gmail.com" -> DoctorData(name, "Men Miriyem", logo =  R.drawable.miri, sahe=sahe)
                            "renabayramli@gmail.com" -> DoctorData(name, "Men Renayam", logo =R.drawable.rena,sahe =sahe)
                            "serxan@gmail.com" -> DoctorData(name, "Men serxanam",logo =  R.drawable.serxan, sahe = sahe)
                            else -> DoctorData(name, "Men yaxsi oglanam", logo = R.drawable.doctor,sahe =  sahe)
                        }

                        if (!doctorDataMap.containsKey(email)) {
                            doctorDataMap[email] = doctorData
                        }
                    }
                }

                val userList = doctorDataMap.values.toList()
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