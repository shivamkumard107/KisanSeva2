package com.uttarakhand.kisanseva2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.Item
import kotlinx.android.synthetic.main.activity_upload_inventory.*
import kotlinx.android.synthetic.main.fragment_item_info.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemInfoFragment(private val item: Item?) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_item_info, container, false)
        v.etNameIn.setText(item!!.title)
        v.etCategoryIn.setText(item.category)
        v.etDescIn.setText(item.quality)
        v.etQuantityIn.setText(item.quantity.toString())
        v.etPriceIn.setText(item.price.toString())
        v.etDescriptionIn.setText(item.description)
        if (item.image.substring(0, 4) == "http") Glide.with(requireContext()).load(item.image).into(v.item_image)
        else Glide.with(requireContext()).load("https://buyfreshdtu.xyz" + item.image).into(v.item_image)
        v.btnEditDetails.setOnClickListener { editDetails(item) }
        return v
    }

    private fun editDetails(item: Item) {
        if (etNameIn.text.toString() == "") {
            etName.error = "Add Item Name"
            etName.requestFocus()
        } else if (etCategoryIn.text!!.toString() == "") {
            etCategory.error = "Add Category"
            etCategory.requestFocus()
        } else if (etDescIn.text!!.toString() == "") {
            etDesc.error = "Add Quality"
            etDesc.requestFocus()
        } else if (etQuantityIn.text!!.toString() == "") {
            etQuantity.error = "Add Quantity Available"
            etQuantity.requestFocus()
        } else if (etPriceIn.text!!.toString() == "") {
            etPrice.error = "Add price of item per Kg"
            etPrice.requestFocus()
        } else if (etDescriptionIn.text!!.toString() == "") {
            etDescription.error = "Add a small description of item"
            etDescription.requestFocus()
        } else {
            //Upload
            editDetailsOnDb(item)
        }
    }

    private fun editDetailsOnDb(item: Item) {
        //item id is not available to send in edit API
//        RetrofitClientInstance.getRetrofit(context)
//                ?.create<APIs>()
//                ?.editDetails()
//                ?.enqueue(object : Callback<JsonObject> {
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                        Log.d("EditDetailFail", t.message!!)
//                    }
//
//                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
//                        Log.d("EditDetailSuccess", response.body()!!.toString())
//
//                    }
//
//                })
    }


}