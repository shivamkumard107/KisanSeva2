package com.uttarakhand.kisanseva2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.LogisticInfo

class LogisticAdapter(private val infos: ArrayList<LogisticInfo>,
                      private val context: Context) : RecyclerView.Adapter<LogisticAdapter.ViewHolder>() {

    private val TAG = "LogisticAdapter"
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val link: TextView = itemView.findViewById<TextView>(R.id.textView3)
        val description: TextView = itemView.findViewById<TextView>(R.id.plotTextView)
        val expandableLayout : ConstraintLayout = itemView.findViewById(R.id.expandableLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogisticAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.log_info_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    override fun onBindViewHolder(holder: LogisticAdapter.ViewHolder, position: Int) {
        val info = infos[position]
        holder.name.text = info.name
        holder.link.text = info.link
        holder.description.text = info.description

        val isExpanded: Boolean = info.isExpanded
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

        holder.name.setOnClickListener {
            val log = infos[position]
            log.isExpanded = !log.isExpanded
            notifyDataSetChanged()
        }
    }
}