package com.uttarakhand.kisanseva2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.About_us
import com.uttarakhand.kisanseva2.activities.LogisticsInfoActivity
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.activities.WalletSectionActivity
import com.uttarakhand.kisanseva2.model.FarmerInfo
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_upload_inventory.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    var aboutus: MaterialCardView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        v.findViewById<View>(R.id.cv_logistics).setOnClickListener { upload: View? -> }
        v.findViewById<View>(R.id.cardWallet).setOnClickListener { doc: View? -> }
        aboutus = v.findViewById(R.id.cardAbout)
        v.tvCreditScore.setOnClickListener { showAlert() }
        v.cardAbout.setOnClickListener { startActivity(Intent(context, About_us::class.java)) }
        v.cv_logistics.setOnClickListener { startActivity(Intent(context, LogisticsInfoActivity::class.java)) }
        v.cardWallet.setOnClickListener { startActivity(Intent(context, WalletSectionActivity::class.java)) }
        initData()
        return v
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        //set title for alert dialog
        builder.setTitle("Credit Score Info")
        //set message for alert dialog
        builder.setMessage("You are eligible for loan upload 3 lakhs. \nCredit Score is computed on the basis of \n\n1. Quality of your crop, your yield, \n2. Your past production, \n3. Soil quality, \n4. Farmers rating and \n5. Weather condition in your area")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //performing positive action
        builder.setPositiveButton("Ok") { dialogInterface, which ->

        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun initData() {
        RetrofitClientInstance.getRetrofit(context)
                ?.create<APIs>()
                ?.farmerInfo
                ?.enqueue(object : Callback<FarmerInfo> {
                    override fun onFailure(call: Call<FarmerInfo>, t: Throwable) {
                        Log.d("GiftOnFailure", t.message!!)
                    }

                    override fun onResponse(
                            call: Call<FarmerInfo>,
                            response: Response<FarmerInfo>
                    ) {
                        Log.d("GiftOnResponse", response.body()!!.toString())
                        tv_farmer_name.text = response.body()!!.data.name
                        tv_place.text = response.body()!!.data.address.plus(", " + response.body()!!.data.district)
                    }
                })
    }

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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}