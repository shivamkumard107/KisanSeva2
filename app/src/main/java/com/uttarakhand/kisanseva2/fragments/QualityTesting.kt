package com.uttarakhand.kisanseva2.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.QualityTestResultActivity
import kotlinx.android.synthetic.main.fragment_quality_testing.*
import kotlinx.android.synthetic.main.fragment_quality_testing.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [QualityTesting.newInstance] factory method to
 * create an instance of this fragment.
 */
class QualityTesting : Fragment(), OnItemSelectedListener {
    var etCategory: EditText? = null

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    private lateinit var category: Array<String?>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_quality_testing, container, false)
        category = arrayOf<String?>(requireActivity().getString(R.string.select_category), requireActivity().getString(R.string.wheat), getString(R.string.rice), requireActivity().getString(R.string.other))

        etCategory = v.findViewById(R.id.et_title)
        v.findViewById<View>(R.id.imageButton).setOnClickListener { v1: View? -> }
        val spin = v.findViewById<View>(R.id.spinner_category) as Spinner
        spin.onItemSelectedListener = this

        //Creating the ArrayAdapter instance having the country list
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, category)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spin.adapter = aa

        v.imageButton.setOnClickListener { selectImage(v) }
        v.submitPost.setOnClickListener { connectToServer() }
        return v
    }

    @SuppressLint("SetTextI18n")
    private fun connectToServer() {
        tvMessage.visibility = View.VISIBLE
        tvMessage.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        if (!imageSelected) {
            tvMessage.text = requireActivity().getString(R.string.select_category)
        } else if (category[0]!! == spinner_category.selectedItem) {
            tvMessage.text = requireActivity().getString(R.string.select_category)
        } else if (category[3] == spinner_category.selectedItem && etCategory!!.text.toString().trim() == "") {
            tvMessage.text = requireActivity().getString(R.string.speicify_category)
        } else {
            tvMessage.setTextColor(resources.getColor(R.color.green_light))
            tvMessage.text = "Uploading... Please wait!"
            pb_testing.visibility = View.VISIBLE
            submitPost.visibility = View.GONE
            val handler = Handler()
            handler.postDelayed({
                pb_testing.visibility = View.GONE
                submitPost.visibility = View.VISIBLE
                val intent = Intent(context, QualityTestResultActivity::class.java)
                intent.putExtra("category", spinner_category.selectedItem.toString())
                startActivity(intent)
            }, 3000)
        }
    }

    private val SELECT_MULTIPLE_IMAGES = 0

    fun selectImage(v: View?) {
        val intent = Intent()
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_MULTIPLE_IMAGES)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        Toast.makeText(context, category[position], Toast.LENGTH_SHORT).show()
        if (requireContext().getString(R.string.other) == category[position]) {
            etCategory!!.visibility = View.VISIBLE
        } else {
            etCategory!!.visibility = View.GONE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

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
         * @return A new instance of fragment QualityTesting.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): QualityTesting {
            val fragment = QualityTesting()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private var imageSelected: Boolean = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            if (requestCode == SELECT_MULTIPLE_IMAGES && resultCode == Activity.RESULT_OK && null != data) {
                // When a single image is selected.
                if (data.data != null) {
                    var uri = data.data
                    Log.d("ImageDetails", "Single Image URI : $uri")
                    uri = data.data
                    imageSelected = true
                    imageButton.setBackgroundResource(android.R.color.transparent)
                    imageButton.setImageURI(uri)
                    Log.i("URI_pic", uri.toString())
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}