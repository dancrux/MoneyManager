package com.cruxrepublic.moneymanager.ui.sent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.data.model.Sent
import com.cruxrepublic.moneymanager.ui.income.IncomeAdapter
import com.cruxrepublic.moneymanager.ui.utils.SentRecyclerClickListener
import kotlinx.android.synthetic.main.received_item_view.view.*

class SentAdapter : RecyclerView.Adapter<SentAdapter.ViewHolder>() {
    var data = mutableListOf<Sent>()
    var listener: SentRecyclerClickListener? = null
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    fun setSent(sent: List<Sent>){
        this.data = sent as MutableList<Sent>
        sent.reverse()
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val receiversId: TextView = itemView.findViewById(R.id.sourceText)
        val amount: TextView = itemView.findViewById(R.id.amountText)
        val time: TextView = itemView.findViewById(R.id.incomeDateTimeText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.received_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sent = data[position]
        holder.receiversId.text = sent.receiversId.toString()
        holder.amount.text = sent.amount.toString()
        holder.time.text = sent.time.toString()
        holder.itemView.deleteButton.setOnClickListener { listener?.onItemClicked(it, sent) }
    }
}