package com.uttarakhand.kisanseva2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.FarmerInventoryAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.LoginActivity
import com.uttarakhand.kisanseva2.activities.UploadInventoryActivity
import com.uttarakhand.kisanseva2.model.FarmerInfo
import com.uttarakhand.kisanseva2.network.APIs
import com.uttarakhand.kisanseva2.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        initData(v)
        v.fab_add_item.setOnClickListener { addItem() }
        return v
    }

    private fun addItem() {
        startActivity(Intent(context, UploadInventoryActivity::class.java))
    }

    private fun initData(v: View) {
        v.pb_inventory.visibility = View.VISIBLE
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
                        if (response.code() != 200) {
                            startActivity(Intent(context, LoginActivity::class.java))
                            activity!!.finish()
                        }
                        pb_inventory.visibility = View.GONE
                        if (response.body() != null) {
                            Log.d("GiftOnResponse", response.body()!!.toString())
                            initRecycler(response.body()!!, v)
                        } else {
                            Log.d("GiftOnResponse", response.message().toString())
                        }
                    }
                })
    }

    private fun initRecycler(body: FarmerInfo, v: View) {
        if (body.data.items.isNotEmpty()) {
            rvInventory.adapter = FarmerInventoryAdapter(body, context)
            rvInventory.layoutManager = LinearLayoutManager(context)
        } else {
            v.no_item.visibility = View.VISIBLE
        }

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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}