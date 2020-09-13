package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.NegotiatedAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.negotiation.Negotiation
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_negotiated.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NegotiatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_negotiated)
        supportActionBar!!.title = getString(R.string.negotiated_prices)
        initData()
    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.allNegotiations
                ?.enqueue(object : Callback<Negotiation> {
                    override fun onFailure(call: Call<Negotiation>, t: Throwable) {
                        Toast.makeText(this@NegotiatedActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("NegoFail", t.message!!)
                    }

                    override fun onResponse(call: Call<Negotiation>, response: Response<Negotiation>) {
                        Toast.makeText(this@NegotiatedActivity, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d("NegoSuccess", response.body()!!.toString())
                        rvNegotiatedPrice.adapter = NegotiatedAdapter(response.body()!!, this@NegotiatedActivity)
                        rvNegotiatedPrice.layoutManager = LinearLayoutManager(this@NegotiatedActivity)
                    }
                })
    }
}