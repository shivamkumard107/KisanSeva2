package com.uttarakhand.kisanseva2.fragments

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.model.Constants
import com.uttarakhand.kisanseva2.model.IsLoggedIn
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.concurrent.TimeUnit

class VerifyFragment : Fragment() {
    private var verificationId: String? = null
    private var mAuth: FirebaseAuth? = null
    private var phoneNumber: String? = null
    private lateinit var pinEntryEditText: PinEntryEditText
    private val mCallBack: OnVerificationStateChangedCallbacks = object : OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            val code = phoneAuthCredential.smsCode
            if (code != null) {
                pinEntryEditText!!.setText(code)
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(context, "Error : " + e.message, Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(s, forceResendingToken)
            verificationId = s
            Toast.makeText(context, "Verification Code Sent", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithCredential(credential)
//        RetrofitClientInstance.getRetrofit(context)
//                ?.create<APIs>()
//                ?.submitOtp(code)
//                ?.enqueue(object : Callback<JsonObject> {
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                        if (response.body() != null) {
//                            if (response.body()!!.get("message").equals("")) {
//
//                            }
//                        }
//                    }
//
//                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_verify, container, false)
        val verify = view.findViewById<Button>(R.id.btnVerify)
        pinEntryEditText = view.findViewById(R.id.etCode)
        val bundle = arguments
        if (bundle != null) {
            phoneNumber = bundle.getString(Constants.PHONE_NUMBER)
            val phn_no = "+91$phoneNumber"
            sendVerificationCode(phn_no)
        }
        verify.setOnClickListener { v: View? ->
            pinEntryEditText.setError(false)
            val code = pinEntryEditText.getText().toString()
            if (code.isEmpty() || code.length < 6) {
                pinEntryEditText.setError(true)
                pinEntryEditText.requestFocus()
                return@setOnClickListener
            }
            verifyCode(code)
        }
        return view
    }

    private fun sendVerificationCode(number: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        )
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        isLoggedIn()
                    } else {
                        Toast.makeText(context, "Invalid code! Go back and try again", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun isLoggedIn(): Boolean {
        var isLoggedIn = false
        RetrofitClientInstance.getRetrofit(context)
                ?.create<APIs>()
                ?.isLoggedIn
                ?.enqueue(object : Callback<IsLoggedIn> {
                    override fun onFailure(call: Call<IsLoggedIn>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("IsLoggedInFailure", t.message.toString())
                    }

                    override fun onResponse(call: Call<IsLoggedIn>, response: Response<IsLoggedIn>) {
                            Log.d("IsLoggedInResp", response.code().toString())
                            if (response.code() == 200) {
                                PreferenceManager
                                        .getDefaultSharedPreferences(context)
                                        .edit().putString("token", FirebaseAuth.getInstance().currentUser!!.uid)
                                        .apply()
                                startActivity(Intent(context, MainActivity::class.java))
                                activity!!.finish()
                            } else {
                                val bundle = Bundle()
                                bundle.putString(Constants.PHONE_NUMBER, phoneNumber)
                                val basicDetailsFragment = BasicDetailsFragment()
                                basicDetailsFragment.arguments = bundle
                                val fr = requireActivity().supportFragmentManager.beginTransaction()
                                fr.replace(R.id.container, basicDetailsFragment)
                                fr.addToBackStack(null).commit()
                            }
                        }

                })
        return isLoggedIn
    }
}