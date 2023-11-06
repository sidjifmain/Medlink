package com.mcss.medlink.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mcss.medlink.DoctorProfile
import com.mcss.medlink.R
import com.mcss.medlink.dataClasses.DoctorData

class DoctorAdapter(private var mList: List<DoctorData>) :
    RecyclerView.Adapter<DoctorAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.doctor_card_image)
        val name: TextView = itemView.findViewById(R.id.doctor_card_name)
        val melumat: TextView = itemView.findViewById(R.id.doctor_card_melumat)
        val itemButton: Button = itemView.findViewById(R.id.bookButton) // Butonunuzu ekleyin (itemButton yerine kendi butonunuzun ID'sini kullanın)

        init {
            itemButton.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DoctorProfile::class.java) // Hedef aktivitenin adını ve sınıfını belirtin
                context.startActivity(intent)
            }
        }
    }

    fun setFilteredList(mList: List<DoctorData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_card , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.image.setImageResource(mList[position].logo)
        holder.name.text = mList[position].name
        holder.melumat.text = mList[position].melumat

        holder.itemButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DoctorProfile::class.java)
            context.startActivity(intent)
        }

    }

    fun updateData(newData: List<DoctorData>) {
        mList = newData
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mList.size
    }
}