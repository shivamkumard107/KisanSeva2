package com.uttarakhand.kisanseva2.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.Adapter.MainAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.GodownsActivity
import com.uttarakhand.kisanseva2.activities.generalInfo.*
import com.uttarakhand.kisanseva2.model.MainListItem
import kotlinx.android.synthetic.main.fragment_information.view.*
import java.util.*

class InformationFragment : Fragment() {
    private var list: ArrayList<MainListItem>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: MainAdapter? = null
    private val imageUrls = arrayOf(R.raw.godown, R.raw.crop_production_opt, R.raw.treat, R.raw.shc2,  /*R.raw.production_main,*/R.raw.horticulture_main, R.raw.govp)
    private val englishTexts = arrayOf(R.string.godown, R.string.crop_production_card_title, R.string.treatment_card_title,
            R.string.storage_card_title,
            R.string.horticulture_card_title, R.string.policy_card_title)
    var intent: Intent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val v = inflater.inflate(R.layout.fragment_information, container, false)
        val links = arrayOf(
                Intent(context, GodownsActivity::class.java),
                Intent(context, CropProductionActivity::class.java),
                Intent(context, SelectProblemActivity::class.java),
                Intent(context, SoilHealthActivity::class.java),
                Intent(context, HorticultureActivity::class.java),
                Intent(context, SelectPolicyActivity::class.java)
        )
        list = ArrayList()
        for (i in imageUrls.indices) {
            val item = MainListItem()
            item.imageUrl = imageUrls[i]
            item.englishText = englishTexts[i]
            item.intent = links[i]
            list!!.add(item)
        }
        adapter = MainAdapter(context, list)
        v.recycler.layoutManager = LinearLayoutManager(context)
        v.recycler.adapter = adapter
        Log.v("version", Build.VERSION.SDK_INT.toString() + "")
        v.findViewById<View>(R.id.progress).visibility = View.GONE
        return v
    }
}