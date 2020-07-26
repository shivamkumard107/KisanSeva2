package com.uttarakhand.kisanseva2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.uttarakhand.kisanseva2.R

/**
 * A simple [Fragment] subclass.
 * Use the [QualityTesting.newInstance] factory method to
 * create an instance of this fragment.
 */
class QualityTesting : Fragment(), OnItemSelectedListener {
    var country = arrayOf<String?>("Select Category", "India", "USA", "China", "Japan", "Other")
    var etCategory: EditText? = null

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_quality_testing, container, false)
        etCategory = v.findViewById(R.id.et_title)
        v.findViewById<View>(R.id.imageButton).setOnClickListener { v1: View? -> }
        val spin = v.findViewById<View>(R.id.spinner_category) as Spinner
        spin.onItemSelectedListener = this

        //Creating the ArrayAdapter instance having the country list
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(context!!, android.R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spin.adapter = aa
        return v
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        Toast.makeText(context, country[position], Toast.LENGTH_SHORT).show()
        if ("Other" == country[position]) {
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
}