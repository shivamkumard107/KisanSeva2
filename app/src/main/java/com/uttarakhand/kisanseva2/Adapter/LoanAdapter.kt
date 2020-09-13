package com.uttarakhand.kisanseva2.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.blockchain.BlockchainResponse
import java.sql.Timestamp
import java.util.*

class LoanAdapter(private val body: BlockchainResponse, private val context: Context) : RecyclerView.Adapter<LoanAdapter.ViewHolder>() {
    private val bankNames = arrayOf("Pradhan Mantri Fasal Bima Yojana",
            "SIDBI Make in India Loan for Enterprises (SMILE)",
            "Pradhan Mantri Mudra Yojana (PMMY)",
            "ICCI Bank", "SBI Bank")
    private val status = arrayOf("Approved and Received", "Loan to be payed")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bankName: TextView = itemView.findViewById<TextView>(R.id.tvBankName)
        val transactionId: TextView = itemView.findViewById<TextView>(R.id.tvTransactionId)
        val mDate = itemView.findViewById<TextView>(R.id.date)
        val loanStatus = itemView.findViewById<TextView>(R.id.loan_status)
        val mAmount = itemView.findViewById<TextView>(R.id.loan_amount)
        val genesis = itemView.findViewById<TextView>(R.id.genesis)
        val credit = itemView.findViewById<TextView>(R.id.credit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.loan_info_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return body.data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LoanAdapter.ViewHolder, position: Int) {
        if (position != 0) {
            holder.bankName.text = "Loan Provider: ${bankNames[getRandomNumber(0, 4)]}"
            holder.transactionId.text = body.data[position]._id
            holder.mDate.text = Date(Timestamp(body.data[position].data.timestamp).time).toString()
            holder.mAmount.text = "Loan Amount: ${body.data[position].data.transactions[0].amount.toString()}"
            holder.loanStatus.text = "<----${status[getRandomNumber(0, 2)]}--->"
            holder.credit.text = "Credit Score: ${body.data[position].data.transactions[0].credit_score}"
        } else {
            holder.bankName.visibility = View.INVISIBLE
            holder.transactionId.visibility = View.INVISIBLE
            holder.mDate.visibility = View.INVISIBLE
            holder.mAmount.visibility = View.INVISIBLE
            holder.loanStatus.visibility = View.INVISIBLE
            holder.genesis.visibility = View.VISIBLE
        }
    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }
}