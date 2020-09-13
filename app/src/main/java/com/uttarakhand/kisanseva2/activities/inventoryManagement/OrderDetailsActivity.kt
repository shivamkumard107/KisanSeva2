package com.uttarakhand.kisanseva2.activities.inventoryManagement

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
        tvPhone.text = order.buyer.address
        tvPhone.text = order.buyer.phone
        tvDate.text = order.transactionId.createdAt
        tvAmount.text = order.amount.toString()
        textView4.text = "ID${order.transactionId.receiptNumber.toString()}"
        order_status.text = if (order.completed) "Ordered" else "Delivered"
        ivMapsIntent.setOnClickListener { sendToMaps() }
        ivCallIntent.setOnClickListener { sendToCall(order.buyer.phone) }
    }

    private fun sendToCall(phone: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        startActivity(intent)
    }

    private fun sendToMaps() {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=29.4039293,78.7721041&daddr=30.3916978,78.3413935"))
        startActivity(intent)
    }
}