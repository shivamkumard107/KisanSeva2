package com.uttarakhand.kisanseva2.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.WalletTransactionsAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders
import com.uttarakhand.kisanseva2.model.allOrders.OrdersWalletResolved
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_wallet_section.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class WalletSectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_section)
        supportActionBar!!.title = getString(R.string.earning)
        initData()
        swipe_wallet.setOnRefreshListener {
            initData()
        }
    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.allOrders
                ?.enqueue(object : Callback<AllOrders> {
                    override fun onFailure(call: Call<AllOrders>, t: Throwable) {
                        Log.d("WalletError", t.message.toString())
                        Toast.makeText(this@WalletSectionActivity, t.message, Toast.LENGTH_SHORT).show()
                        swipe_wallet.isRefreshing = false
                    }

                    override fun onResponse(call: Call<AllOrders>, response: Response<AllOrders>) {
                        Log.d("WalletResponse", response.body()!!.toString())
                        initRecycler(response.body()!!)
                        setBalance(response.body()!!)
                        swipe_wallet.isRefreshing = false
                    }
                })
    }

    @SuppressLint("SetTextI18n")
    private fun setBalance(body: AllOrders) {
        var totalBalance = 0;
        for (i in body.data) {
            totalBalance += i.amount
        }
        tvBalance.text = "₹ $totalBalance"
    }

    private fun initRecycler(body: AllOrders) {
        var list: ArrayList<OrdersWalletResolved> = ArrayList()
        for (i in body.data) {
            val ordersWalletResolved: OrdersWalletResolved = OrdersWalletResolved()
            ordersWalletResolved.setmAmount(i.amount.toString())
            ordersWalletResolved.setmBuyerName(i.buyer.first_name)
            Log.d("CreatedAt", i.transactionId.createdAt)
            ordersWalletResolved.setmTime(i.transactionId.createdAt.toString())
            list.add(ordersWalletResolved)
        }
        rvTransactions.adapter = WalletTransactionsAdapter(list, this)
        rvTransactions.layoutManager = LinearLayoutManager(this)
    }
}