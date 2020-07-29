package com.uttarakhand.kisanseva2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.uttarakhand.kisanseva2.Adapter.RatingReviewAdapter
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.RatingReview
import kotlinx.android.synthetic.main.fragment_rating_reviews.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RatingReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RatingReviewsFragment : Fragment() {

    private var ratingReviews: ArrayList<RatingReview>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_rating_reviews, container, false)
        initData()
        initRecycler(v)

        return v
    }

    private fun initData() {
        // TOdo get Data From Firebase later
        ratingReviews = ArrayList()
        ratingReviews!!.add(RatingReview("https://image.flaticon.com/icons/svg/847/847969.svg",
                "Krishna",
                4.4,
                "The quality of the item is very good.", "29th Feb 2020"))

        ratingReviews!!.add(RatingReview("https://image.flaticon.com/icons/svg/847/847969.svg",
                "Bimla",
                3.5,
                "Packaging achha tha!!", "1st August 2020"))
    }

    private fun initRecycler(v: View) {
        v.rvRatings.layoutManager = LinearLayoutManager(context)
        v.rvRatings.adapter = RatingReviewAdapter(ratingReviews!!, context)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RatingReviewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RatingReviewsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}