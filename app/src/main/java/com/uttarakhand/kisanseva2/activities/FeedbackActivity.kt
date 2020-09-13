package com.uttarakhand.kisanseva2.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.activity_feedback.btnUploadItem
import kotlinx.android.synthetic.main.activity_upload_inventory.*
import kotlinx.android.synthetic.main.activity_upload_inventory.etDescIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        btnUploadItem.setOnClickListener {
            if (etDescIn.text.toString() == "") {
                etDesc1.error = "Add Name"
                etDesc1.requestFocus()
            } else {
                initData()
            }

        }
    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(this)
                ?.create<APIs>()
                ?.submitGrievance(etDescIn.text.toString(), "Rice", "Premium Quality Rice")
                ?.enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(this@FeedbackActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("FeedFail", t.message!!)
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        Toast.makeText(this@FeedbackActivity, "Loan Applied Successfully", Toast.LENGTH_LONG).show()
                        finish()
                    }
                })
    }
}