package com.uttarakhand.kisanseva2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.Constants
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneLoginFragment : Fragment() {
    var textInputLayout: TextInputLayout? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_phone_login, container, false)
        textInputLayout = view.findViewById(R.id.tilPhoneNumber)
        val submit = view.findViewById<Button>(R.id.btnGetCode)
        submit.setOnClickListener { v: View? -> sendOtp() }
        return view
    }

    private fun sendOtp() {
        val phn_no = textInputLayout!!.editText!!.text.toString()
        if (phn_no.length != 10) {
            textInputLayout!!.error = "Valid number is required"
            textInputLayout!!.requestFocus()
        } else {
            val bundle = Bundle()
            bundle.putString(Constants.PHONE_NUMBER, phn_no)
            val verifyFragment = VerifyFragment()
            verifyFragment.arguments = bundle
            val fr = requireActivity().supportFragmentManager.beginTransaction()
            fr.replace(R.id.container, verifyFragment)
            fr.addToBackStack(null).commit()
//            sendOtpRequest(phn_no)
        }
    }

//    private fun sendOtpRequest(phnNo: String) {
//        RetrofitClientInstance.getRetrofit(context)
//                ?.create<APIs>()
//                ?.sendNumber(phnNo)
//                ?.enqueue(object : Callback<JsonObject> {
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                        if (response.body() != null) {
//                            Log.d("OTPFragment", response.body()!!.get("message").toString())
//                            if (response.body()!!.get("message").toString() == "\"OTP SENT\"") {
//                                val bundle = Bundle()
//                                bundle.putString(Constants.PHONE_NUMBER, phnNo)
//                                val verifyFragment = VerifyFragment()
//                                verifyFragment.arguments = bundle
//                                val fr = requireActivity().supportFragmentManager.beginTransaction()
//                                fr.replace(R.id.container, verifyFragment)
//                                fr.addToBackStack(null).commit()
//                            } else {
//                                Toast.makeText(context, response.body()!!.get("message").toString(), Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//                })
//    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PhoneLoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): PhoneLoginFragment {
            val fragment = PhoneLoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}