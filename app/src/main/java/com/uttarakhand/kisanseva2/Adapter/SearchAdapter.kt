package com.uttarakhand.kisanseva2.Adapter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.activities.MainActivity
import com.uttarakhand.kisanseva2.activities.NegotiatedActivity
import com.uttarakhand.kisanseva2.activities.generalInfo.SoilHealthActivity
import com.uttarakhand.kisanseva2.activities.inventoryManagement.OrdersInfoActivity
import com.uttarakhand.kisanseva2.model.SearchFeature
import java.util.*

class SearchAdapter(private val searchFeatures: ArrayList<SearchFeature>,
                    private val context: Context, private val activity: Activity) :
        RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById<ImageView>(R.id.icon)
        val name = itemView.findViewById<TextView>(R.id.name)
        val cvSearch = itemView.findViewById<CardView>(R.id.cvSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val itemValue = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(itemValue)
    }

    override fun getItemCount(): Int {
        return searchFeatures.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.icon.setImageResource(searchFeatures[position].getmFeatureIcon())
        holder.name.text = searchFeatures[position].getmFeatureName()
        holder.cvSearch.setOnClickListener { openCard(searchFeatures[position].getmFeatureMessage(), context) }
    }

    private fun openCard(getmFeatureMessage: String?, context: Context) {
        when (getmFeatureMessage) {
            "inventory" -> {
                val i = Intent(this.context, MainActivity::class.java)
                i.putExtra("open", "inventory")
                context.startActivity(i)
            }
            "information" -> {
                val i = Intent(this.context, MainActivity::class.java)
                i.putExtra("open", "information")
                context.startActivity(i)
            }
            "quality" -> {
                val i = Intent(this.context, MainActivity::class.java)
                i.putExtra("open", "quality")
                context.startActivity(i)
            }
            "chat" -> {
                val i = Intent(this.context, MainActivity::class.java)
                i.putExtra("open", "chat")
                context.startActivity(i)
            }
            "profile" -> {
                val i = Intent(this.context, MainActivity::class.java)
                i.putExtra("open", "profile")
                context.startActivity(i)
            }
            "orders" -> {
                val i = Intent(this.context, OrdersInfoActivity::class.java)
                i.putExtra("open", "orders")
                context.startActivity(i)
            }
            "negotiate" -> {
                val i = Intent(this.context, NegotiatedActivity::class.java)
                i.putExtra("open", "negotiate")
                context.startActivity(i)
            }
            "language" -> {
                changeLanguage()
            }
            "soil" -> {
                val i = Intent(this.context, SoilHealthActivity::class.java)
                context.startActivity(i)
            }
        }
    }

    private fun changeLanguage() {
        val items = arrayOf("Hindi", "English")
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Choose Language...")
        alertDialogBuilder.setSingleChoiceItems(items, -1,
                DialogInterface.OnClickListener { dialog, which ->
                    if (which == 0) {
                        setLocale("hi")
                        activity.recreate()
                    } else if (which == 1) {
                        setLocale("en")
                        activity.recreate()
                    }
                    dialog.dismiss()
                })
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

}