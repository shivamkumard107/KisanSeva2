package com.uttarakhand.kisanseva2.activities.generalInfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.Adapter.SoilHealthCardAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.ItemHealthCard
import com.uttarakhand.kisanseva2.utilities.SqliteHelper
import kotlinx.android.synthetic.main.activity_soil_health.*
import java.util.*

class SoilHealthActivity : AppCompatActivity() {
    private var helper: SqliteHelper? = null
    private var spinner1: Spinner? = null
    private var spinner2: Spinner? = null
    private var btnSubmit: Button? = null
    var list1: ArrayList<String>? = null
    var list2: ArrayList<String>? = null
    var card_list: ArrayList<ItemHealthCard>? = null
    var adapter: SoilHealthCardAdapter? = null
    var arrayAdapter1: ArrayAdapter<String>? = null
    var arrayAdapter2: ArrayAdapter<String>? = null
    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soil_health)
        supportActionBar!!.setTitle(R.string.labs_heading)
        spinner1 = findViewById<View>(R.id.spinner1) as Spinner
        spinner2 = findViewById<View>(R.id.spinner2) as Spinner
        btnSubmit = findViewById<View>(R.id.btnSubmit) as Button
        list1 = ArrayList()
        list2 = ArrayList()
        helper = SqliteHelper(this)
        list1 = helper!!.distinctStates
        Toast.makeText(this@SoilHealthActivity, R.string.soil_test_lab, Toast.LENGTH_LONG).show()
        arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list1!!)
        arrayAdapter1!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1!!.adapter = arrayAdapter1
        spinner1!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Log.v("positon1", "" + position)
                list2 = helper!!.getDistricts(spinner1!!.getItemAtPosition(position) as String)
                arrayAdapter2 = ArrayAdapter(this@SoilHealthActivity, android.R.layout.simple_spinner_dropdown_item, list2!!)
                arrayAdapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner2!!.adapter = arrayAdapter2
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        list2!!.add(getString(R.string.select_district))
        arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list2!!)
        arrayAdapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2!!.adapter = arrayAdapter2
        btnSubmit!!.setOnClickListener {
            if (spinner1!!.selectedItemPosition != 0 && spinner2!!.selectedItemPosition != 0) {
                card_list = helper!!.getHealthCard(spinner1!!.selectedItem.toString() + "", spinner2!!.selectedItem.toString() + "")
                adapter = SoilHealthCardAdapter(this@SoilHealthActivity, card_list)
                recyclerView = findViewById<View>(R.id.recycler) as RecyclerView
                recyclerView!!.layoutManager = LinearLayoutManager(this@SoilHealthActivity)
                recyclerView!!.adapter = adapter
            }
        }
        floatingActionButton.setOnClickListener { openFileChooser() }
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            pb_upload.visibility = View.VISIBLE
            floatingActionButton.visibility = View.INVISIBLE
            val handler = Handler()
            handler.postDelayed({
                pb_upload.visibility = View.INVISIBLE
                floatingActionButton.visibility = View.VISIBLE
                Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_LONG).show()
            }, 1500)
        }
    }
}