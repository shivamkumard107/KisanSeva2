package com.uttarakhand.kisanseva2.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.negotiation.Negotiation

class NegotiatedAdapter(private val info: Negotiation,
                        private val context: Context) : RecyclerView.Adapter<NegotiatedAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBuyerName = itemView.findViewById<TextView>(R.id.tvBuyerName)
        val tvPhone = itemView.findViewById<TextView>(R.id.tvPhone)
        val tvNegoAmount = itemView.findViewById<TextView>(R.id.tvNegoAmount)
        val tvActualAmount = itemView.findViewById<TextView>(R.id.tvActualAmount)
        val status = itemView.findViewById<TextView>(R.id.status)
        val ivProductImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
        val tvQuality = itemView.findViewById<TextView>(R.id.district)
        val tvQuantity = itemView.findViewById<TextView>(R.id.tvQuantity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NegotiatedAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.negotiated_price, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return info.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NegotiatedAdapter.ViewHolder, position: Int) {
        val data = info.data[position]
        holder.tvBuyerName.text = data.buyer.first_name
        holder.tvPhone.text = data.buyer.phone
        holder.tvNegoAmount.text = "₹ ${data.praposedPrice}"
        holder.tvActualAmount.text = "₹ ${data.item.price}"
        holder.status.text = if (!data.status) "Negotiated By Buyer, Take Action" else "You and Buyer agreed to price"

        if (data.item.image.substring(0, 4) == "http") Glide.with(context).load(data.item.image).into(holder.ivProductImage)
        else Glide.with(context).load("https://buyfreshdtu.xyz" + data.item.image).into(holder.ivProductImage)

        holder.tvCategory.text = data.item.title
        holder.tvQuality.text = data.item.quality
        holder.tvQuantity.text = data.quantity.toString()
    }
}