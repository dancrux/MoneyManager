package com.cruxrepublic.moneymanager.ui.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.ui.income.IncomeAdapter

class ExpensesAdapter: RecyclerView.Adapter<ExpensesAdapter.ViewHolder>() {
    var data = mutableListOf<Expense>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    fun setExpense(expense: List<Expense>){
        this.data = expense as MutableList<Expense>
        expense.reverse()
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val reason: TextView = itemView.findViewById(R.id.sourceText)
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
        val expense = data[position]
        holder.reason.text = expense.reasonForExpenses.toString()
        holder.amount.text = expense.amount.toString()
        holder.time.text = expense.time.toString()
    }
}