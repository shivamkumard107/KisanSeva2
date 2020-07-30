package com.uttarakhand.kisanseva2.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonObject
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.model.Constants
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.fragment_basic_details.*
import kotlinx.android.synthetic.main.fragment_basic_details.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasicDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasicDetailsFragment : Fragment() {
    private var phoneNumber: String? = null
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_basic_details, container, false)
        val bundle = arguments
        v.uploadAadhar.setOnClickListener {
            openFileChooser()
        }
        if (bundle != null) {
            phoneNumber = bundle.getString(Constants.PHONE_NUMBER)
        }
        v.btnGetCode.setOnClickListener { uploadBasicInfo() }
        return v
    }

    private fun uploadBasicInfo() {
        if (etNameIn.text.toString() == "") {
            etName.error = "Enter Name"
            etName.requestFocus()
        } else if (etAddressIn.text.toString() == "") {
            etAddress.error = "Enter Address"
            etAddress.requestFocus()
        } else if (etEmailIn.text.toString() == "") {
            etAddress.error = "Enter Email"
            etAddress.requestFocus()
        } else if (etDistrictIn.text.toString() == "") {
            etDistrict.error = "Enter District"
            etDistrict.requestFocus()
        } else if (!imageSelected) {
            Toast.makeText(context, "Upload Aadhar Card", Toast.LENGTH_SHORT).show()
        } else {
            sendRequest()
        }


    }

    private val PICK_IMAGE_REQUEST = 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && null != data) {
                // When a single image is selected.
                if (data.data != null) {
                    var uri = data.data
                    Log.d("ImageDetails", "Single Image URI : $uri")
                    uri = data.data
                    imageSelected = true

                    uploaded.visibility = View.GONE
                    pb_uploader.visibility = View.VISIBLE
                    uploadAadhar.visibility = View.GONE
                    val handler = Handler()
                    handler.postDelayed({
                        uploaded.visibility = View.VISIBLE
                        pb_uploader.visibility = View.GONE
                        uploadAadhar.visibility = View.VISIBLE
                    }, 2000)
                    Log.i("URI_pic", uri.toString())
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    //This is to upload AADHAR Card Pic on the server @ps
    private var imageSelected: Boolean = false

    private fun openFileChooser() {
        imageSelected = false
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun sendRequest() {
        RetrofitClientInstance.getRetrofit(context)
                ?.create<APIs>()
                ?.uploadInformation(phoneNumber,
                        etAddressIn.text.toString(),
                        etNameIn.text.toString(),
                        etDistrictIn.text.toString(),
                        FirebaseAuth.getInstance().currentUser!!.uid,
                        etEmailIn.text.toString())
                ?.enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.d("UploadingError", t.message.toString())
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        Log.d("UploadingResponse", response.body()!!.get("access_token").toString())
                        if (response.body() != null) {
                            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("token", response.body()!!.get("access_token").asString).apply()
                            startActivity(Intent(context, MainActivity::class.java))
                            activity!!.finish()
                        }
                    }

                })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BasicDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BasicDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}