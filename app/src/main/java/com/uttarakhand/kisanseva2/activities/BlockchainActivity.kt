package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.LoanAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.blockchain.BlockchainResponse
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_blockchain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class BlockchainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blockchain)
        supportActionBar!!.title = "Loans Ledger"
        initData()
    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.loanInfo()
                ?.enqueue(object : Callback<BlockchainResponse> {
                    override fun onFailure(call: Call<BlockchainResponse>, t: Throwable) {
                        Toast.makeText(this@BlockchainActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("InfoFail", t.message!!)
                    }

                    override fun onResponse(call: Call<BlockchainResponse>, response: Response<BlockchainResponse>) {
                        Toast.makeText(this@BlockchainActivity, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d("InfoSuccess", response.body()!!.toString())
                        loan_info.layoutManager = LinearLayoutManager(this@BlockchainActivity)
                        loan_info.adapter = LoanAdapter(response.body()!!, this@BlockchainActivity)
                    }

                })
    }
}