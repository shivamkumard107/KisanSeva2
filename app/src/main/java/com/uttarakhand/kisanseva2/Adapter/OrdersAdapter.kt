package com.uttarakhand.kisanseva2.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.inventoryManagement.OrderDetailsActivity
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders
import com.uttarakhand.kisanseva2.model.allOrders.Data

class OrdersAdapter(private val allOrders: AllOrders,
                    private val context: Context) :
        RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buyerName: TextView = itemView.findViewById(R.id.tvBuyerName)
        val address: TextView = itemView.findViewById(R.id.tvPhone)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val items: RecyclerView = itemView.findViewById(R.id.rvItemOrdered)
        val totalAmout: TextView = itemView.findViewById(R.id.tvAmount)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
        val cv: CardView = itemView.findViewById(R.id.cl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.buyer_orders_item_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allOrders.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = allOrders.data[position]
        holder.buyerName.text = order.buyer.first_name
        holder.address.text = order.buyer.address
        holder.date.text = order.transactionId.createdAt
        holder.items.adapter = OrderItemsAdapter(order.orderQuantity, context)
        holder.items.layoutManager = LinearLayoutManager(context)
        holder.totalAmout.text = order.amount.toString()
        holder.status.text = if (order.completed) "Ordered" else "Delivered"
        holder.cv.setOnClickListener { openDetailedOrder(order) }
    }

    private fun openDetailedOrder(order: Data) {
        val i = Intent(context, OrderDetailsActivity::class.java)
        val gson = Gson()
        i.putExtra("order", gson.toJson(order))
        context.startActivity(i)
    }
}