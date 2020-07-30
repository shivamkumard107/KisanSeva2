package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.LogisticAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.LogisticInfo
import kotlinx.android.synthetic.main.activity_logistics_info.*

class LogisticsInfoActivity : AppCompatActivity() {


    private var logList: ArrayList<LogisticInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logistics_info)

        initData()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val logAdapter = LogisticAdapter(logList!!, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = logAdapter
    }


    private fun initData() {
        logList = ArrayList()
        logList!!.add(LogisticInfo(getString(R.string.rivigo), "relay.rivigo.com", getString(R.string.rivigo_desc)))
        logList!!.add(LogisticInfo(getString(R.string.truck_suvidha), " www.trucksuvidha.com", getString(R.string.truck_suvidha_desc)))
        logList!!.add(LogisticInfo(getString(R.string.transin_logistics), "www.transin.in", getString(R.string.Transin_Logistics_desc)))
        logList!!.add(LogisticInfo(getString(R.string.truck_guru), "www.truckguru.co.in", getString(R.string.truck_guru_desc)))
        logList!!.add(LogisticInfo(getString(R.string.elasctic_run), "www.elastic.run", getString(R.string.Elasctic_Run_desc)))
        logList!!.add(LogisticInfo(getString(R.string.blackBuck), "www.blackbuck.com", getString(R.string.BlackBuck_desc)))
        logList!!.add(LogisticInfo(getString(R.string.Mavyn), "www.mavyn.in", getString(R.string.Mavyn_desc)))
        logList!!.add(LogisticInfo(getString(R.string.Jusdac_India_desc), "www.jusdaindia.com", getString(R.string.Jusdac_india_desc)))
        logList!!.add(LogisticInfo(getString(R.string.eParivahan), "www.eparivahan.com", getString(R.string.eParivahan_desc)))

    }
}