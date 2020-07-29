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
import com.uttarakhand.kisanseva2.model.allOrders.OrderQuantity

class OrderItemsAdapter(private val items: List<OrderQuantity>,
                        private val context: Context) : RecyclerView.Adapter<OrderItemsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemInfo: TextView = itemView.findViewById(R.id.item_info)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderItemsAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemInfo.text = "${item.quantity}Kg x ${item.item.title} (${item.item.quality})"
        Glide.with(context).load("https://buyfreshdtu.xyz" + item.item.image).placeholder(context.getDrawable(R.drawable.placeholder)).into(holder.itemImage)
    }
}