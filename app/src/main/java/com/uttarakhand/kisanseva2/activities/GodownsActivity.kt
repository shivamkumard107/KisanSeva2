package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.WarehousesAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.Warehouse
import kotlinx.android.synthetic.main.activity_godowns.*


class GodownsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_godowns)
        val warehouses = ArrayList<Warehouse>()
        val warehouse1 = Warehouse("Rudrapur City", "RUDRAPUR", "Shri Rakesh kumar Dubey", "8864820612", 10486, 0, 0, 0)
        val warehouse2 = Warehouse("Kichha", "RUDRAPUR", "Shri charan singh", "9719743185", 13111, 0, 0, 0)
        val warehouse3 = Warehouse("Sitarganj / Nanakmatta", "RUDRAPUR", "Shri Harsh Mohan Singh Tolia", "7060137608", 8970, 0, 0, 16090)
        val warehouse4 = Warehouse("Gadarpur / Gularbhoj", "NAINITAL", "Shri Devendra Singh Rawat", "7351640330", 20021, 0, 0, 0)
        val warehouse5 = Warehouse("Kashipur", "NAINITAL", "Shri Vipin Kumar Arora", "9639199470", 0, 0, 0, 5000)
        val warehouse6 = Warehouse("Ramnagar", "NAINITAL", "Shri Santos Singh Negi", "9917267644", 0, 0, 0, 2364)
        val warehouse7 = Warehouse("Haldwani Kmaluaganja", "NAINITAL", "Shri Rajeev Pant", "8006077779", 16470, 0, 0, 0)
        val warehouse8 = Warehouse("Haldwani Naveen Mandi", "NAINITAL", "Shri  Rajendra Joshi", "8218630753", 6895, 0, 0, 0)
        val warehouse9 = Warehouse("Almora", "Almora", "Shri Harsh Mohan Singh Tolia", "7060137608", 5000, 0, 0, 0)
        val warehouse10 = Warehouse("Vikash Nagar", "", "Shri Mukesh Bawra", "7606167196", 10613, 0, 0, 0)
        val warehouse11 = Warehouse("Nakronda", "Dehradun", "Shri Ramaotar", "8077801184", 10172, 0, 0, 0)
        val warehouse12 = Warehouse("Haridwar", "Haridwar", "Shri Rakesh Kumar", "9927804210", 5222, 0, 0, 0)

        warehouses.add(warehouse1)
        warehouses.add(warehouse2)
        warehouses.add(warehouse3)
        warehouses.add(warehouse4)
        warehouses.add(warehouse5)
        warehouses.add(warehouse6)
        warehouses.add(warehouse7)
        warehouses.add(warehouse8)
        warehouses.add(warehouse9)
        warehouses.add(warehouse10)
        warehouses.add(warehouse11)
        warehouses.add(warehouse12)
        rvWare.adapter = WarehousesAdapter(warehouses, this)
        rvWare.layoutManager = LinearLayoutManager(this)

//        val mywebview = findViewById<View>(R.id.webView) as WebView
//
//        val link = intent.extras!!.getString("url")
//        mywebview.loadUrl(link)
//        mywebview.settings.javaScriptEnabled = true


    }
}