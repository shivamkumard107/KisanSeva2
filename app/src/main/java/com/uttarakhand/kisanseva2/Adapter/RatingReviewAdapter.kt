package com.uttarakhand.kisanseva2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.RatingReview

class RatingReviewAdapter(private val ratingReviews: ArrayList<RatingReview>,
                          private val context: Context?) : RecyclerView.Adapter<RatingReviewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buyerImage: ImageView = itemView.findViewById(R.id.buyer_image)
        val buyerName: TextView = itemView.findViewById(R.id.buyer_name)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        val description: TextView = itemView.findViewById(R.id.description)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rating_reviews_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ratingReviews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ratingReview = ratingReviews[position]
        Glide.with(context!!).load("https://buyfreshdtu.xyz" + ratingReview.image).placeholder(R.drawable.ic_user).dontAnimate().into(holder.buyerImage);
        holder.buyerName.text = ratingReview.buyerName
        holder.ratingBar.rating = ratingReview.rating.toFloat()
        holder.description.text = ratingReview.desc
        holder.date.text = ratingReview.date
    }
}