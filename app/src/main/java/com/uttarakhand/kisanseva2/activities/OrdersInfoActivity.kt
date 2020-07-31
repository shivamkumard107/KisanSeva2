package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.OrdersAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_orders_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class OrdersInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_info)
        supportActionBar!!.title = getString(R.string.all_orders)
        initData()
    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.allOrders
                ?.enqueue(object : Callback<AllOrders> {
                    override fun onFailure(call: Call<AllOrders>, t: Throwable) {
                        Log.d("OrdersError", t.message.toString())
                        Toast.makeText(this@OrdersInfoActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<AllOrders>, response: Response<AllOrders>) {
                        Log.d("OrdersResponse", response.body()!!.toString())
                        initRecycler(response.body()!!)
                    }
                })
    }

    private fun initRecycler(body: AllOrders) {
        rvOrders.layoutManager = LinearLayoutManager(this)
        rvOrders.adapter = OrdersAdapter(body, this)
    }
}