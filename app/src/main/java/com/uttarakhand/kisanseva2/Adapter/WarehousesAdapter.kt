package com.uttarakhand.kisanseva2.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.model.Warehouse
import kotlinx.android.synthetic.main.activity_upload_inventory.*

class WarehousesAdapter(private val list: ArrayList<Warehouse>, private val context: Context) : RecyclerView.Adapter<WarehousesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvCategory)
        val district = itemView.findViewById<TextView>(R.id.district)
        val offName = itemView.findViewById<TextView>(R.id.tvQuantity)
        val number = itemView.findViewById<TextView>(R.id.tvPrice)
        val self = itemView.findViewById<TextView>(R.id.tvPrice2)
        val private = itemView.findViewById<TextView>(R.id.tvPrice3)
        val cv = itemView.findViewById<CardView>(R.id.cardView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.godown_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val house = list.get(position)
        holder.name.text = house.centerName
        holder.district.text = house.district
        holder.offName.text = house.officerName
        holder.number.text = house.mobileNum
        holder.self.text = house.selfPot.toString()
        holder.private.text = house.privateWarehouse.toString()
        holder.cv.setOnClickListener { showDialog() }

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle("Address Change Request")
        //set message for alert dialog
        builder.setMessage("For the logistics order, Do you want to change the pickup address location of you crop Sharbati Wheat to this Warehouse location?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //performing positive action
        builder.setPositiveButton("Yes Change Location") { dialogInterface, which ->
            Toast.makeText(context, "Location Updated Successfully", Toast.LENGTH_LONG).show()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }
}