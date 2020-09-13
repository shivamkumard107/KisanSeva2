package com.uttarakhand.kisanseva2.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.inventoryManagement.InventoryItemDetailsActivity
import com.uttarakhand.kisanseva2.model.FarmerInfo

class FarmerInventoryAdapter(val infos: FarmerInfo,
                             private val context: Context?) :
        RecyclerView.Adapter<FarmerInventoryAdapter.InventoryViewHolder>() {


    class InventoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardView2)
        val tvCategory: TextView = itemView.findViewById<TextView>(R.id.tvCategory)
        val tvQuality: TextView = itemView.findViewById<TextView>(R.id.district)
        val tvDescription: TextView = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvQuantity: TextView = itemView.findViewById<TextView>(R.id.tvQuantity)
        val tvPrice: TextView = itemView.findViewById<TextView>(R.id.tvPrice)
        val ivImage: ImageView = itemView.findViewById(R.id.ivProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val itemValue = LayoutInflater.from(parent.context).inflate(R.layout.farmers_inventory_items, parent, false)
        return InventoryViewHolder(itemValue)
    }

    override fun getItemCount(): Int {
        return infos.data.items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = infos.data.items[position]
        holder.tvCategory.text = item.title
        holder.tvDescription.text = item.description
        holder.tvPrice.text = "Rs${item.price}/Kg"
        holder.tvQuality.text = item.quality
        holder.tvQuantity.text = "${item.quantity} Kg"
        if (item.image.substring(0, 4) == "http") Glide.with(context!!).load(item.image).into(holder.ivImage)
        else Glide.with(context!!).load("https://buyfreshdtu.xyz" + item.image).into(holder.ivImage)


        holder.cardView.setOnClickListener {
            val i = Intent(context, InventoryItemDetailsActivity::class.java)
            val gson = Gson()
            i.putExtra("item", gson.toJson(item))
            context.startActivity(i)
        }
    }
}