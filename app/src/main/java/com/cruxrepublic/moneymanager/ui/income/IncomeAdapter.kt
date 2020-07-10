package com.cruxrepublic.moneymanager.ui.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Income

 class IncomeAdapter: RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {
    var data = mutableListOf<Income>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

     fun setIncome(income: List<Income>){
         this.data = income as MutableList<Income>
         income.reverse()
         notifyDataSetChanged()
     }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val source: TextView = itemView.findViewById(R.id.sourceText)
       val amount: TextView = itemView.findViewById(R.id.amountText)
       val time: TextView = itemView.findViewById(R.id.incomeDateTimeText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.income_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val income = data[position]
        holder.source.text = income.sourceOfIncome.toString()
        holder.amount.text = income.amount.toString()
        holder.time.text = income.time.toString()
    }
}