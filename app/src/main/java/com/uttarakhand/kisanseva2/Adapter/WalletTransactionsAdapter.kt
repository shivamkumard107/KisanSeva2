package com.uttarakhand.kisanseva2.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.allOrders.OrdersWalletResolved
import de.hdodenhof.circleimageview.CircleImageView

class WalletTransactionsAdapter(private val info: ArrayList<OrdersWalletResolved>,
                                private val context: Context) :
        RecyclerView.Adapter<WalletTransactionsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInfo = itemView.findViewById<TextView>(R.id.tvtransaction_info)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        val tvMoney = itemView.findViewById<TextView>(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletTransactionsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wallet_transaction_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WalletTransactionsAdapter.ViewHolder, position: Int) {
        holder.tvInfo.text = "Payment Received from: ${info[position].getmBuyerName()}"
        holder.tvMoney.text = "₹${info[position].getmAmount()}"
        holder.tvTime.text = info[position].getmTime()
    }
}