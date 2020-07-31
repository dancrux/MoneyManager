package com.cruxrepublic.moneymanager.ui.received

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Received
import com.cruxrepublic.moneymanager.data.model.Sent
import com.cruxrepublic.moneymanager.ui.sent.SentAdapter
import com.cruxrepublic.moneymanager.ui.utils.ReceivedRecyclerClickListener
import kotlinx.android.synthetic.main.received_item_view.view.*

class ReceivedAdapter: RecyclerView.Adapter<ReceivedAdapter.ViewHolder>() {
    var data = mutableListOf<Received>()
    var listener: ReceivedRecyclerClickListener? = null
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    fun setReceived(received: List<Received>){
        this.data = received as MutableList<Received>
       received.reverse()
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sendersId: TextView = itemView.findViewById(R.id.sourceText)
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
        val received = data[position]
        holder.sendersId.text =  received.sendersId.toString()
        holder.amount.text =  received.amount.toString()
        holder.time.text =  received.time.toString()
        holder.itemView.deleteButton.setOnClickListener { listener?.onItemClicked(it, received) }
    }
}