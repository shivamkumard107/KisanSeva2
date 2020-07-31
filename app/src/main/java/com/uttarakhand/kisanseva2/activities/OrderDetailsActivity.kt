package com.uttarakhand.kisanseva2.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.uttarakhand.kisanseva2.Adapter.OrderItemsAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.allOrders.Data
import kotlinx.android.synthetic.main.activity_order_details.*

class OrderDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        supportActionBar!!.title = getString(R.string.order_details)
        val gson = Gson()
        val order = gson.fromJson<Data>(intent.getStringExtra("order"), Data::class.java)
        rvItemOrdered.adapter = OrderItemsAdapter(order.orderQuantity, this)
        rvItemOrdered.layoutManager = LinearLayoutManager(this)
        tvBuyerName.text = order.buyer.first_name
        tvAddress.text = order.buyer.address
        tvPhone.text = order.buyer.phone
        tvDate.text = order.transactionId.createdAt
        tvAmount.text = order.amount.toString()
        textView4.text = "ID${order.transactionId.receiptNumber.toString()}"
        order_status.text = if (order.completed) "Ordered" else "Delivered"
    }
}