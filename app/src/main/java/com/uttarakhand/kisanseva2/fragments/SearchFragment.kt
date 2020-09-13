package com.uttarakhand.kisanseva2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.uttarakhand.kisanseva2.Adapter.SearchAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.SearchFeature
import kotlinx.android.synthetic.main.fragment_search.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        val searchOption = ArrayList<SearchFeature>()
        searchOption.add(SearchFeature("Inventory Crops", R.drawable.ic_home_black_24dp, "inventory"))
        searchOption.add(SearchFeature("Information", R.drawable.ic_info, "information"))
        searchOption.add(SearchFeature("Quality Check", R.drawable.ic_quality, "quality"))
        searchOption.add(SearchFeature("Chat", R.drawable.ic_chat, "chat"))
        searchOption.add(SearchFeature("Profile", R.drawable.ic_user, "profile"))
        searchOption.add(SearchFeature("View your Orders", R.drawable.ic_order, "orders"))
        searchOption.add(SearchFeature("Price Negotiation", R.drawable.ic_contract, "negotiate"))
        searchOption.add(SearchFeature("Change Language", R.drawable.ic_language, "language"))
        searchOption.add(SearchFeature("Soil Testing", R.drawable.ic_testing, "soil"))
        searchOption.add(SearchFeature("Schemes", R.drawable.ic_government, "government"))
        searchOption.add(SearchFeature("Earnings", R.drawable.ic_wallet, "wallet"))
        searchOption.add(SearchFeature("About Us", R.drawable.ic_user, "aboutus"))
        searchOption.add(SearchFeature("Logistics", R.drawable.ic_logistics_search, "logistics"))
        searchOption.add(SearchFeature("Weather", R.drawable.ic_weather, "weather"))


        v.rvSearch.adapter = SearchAdapter(searchOption, requireContext(), requireActivity())
        v.rvSearch.layoutManager = GridLayoutManager(context, 3)
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SearchFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}