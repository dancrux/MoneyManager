package com.cruxrepublic.moneymanager.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R

class ExpenseFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        expenseViewModel =
                ViewModelProvider(this).get(ExpenseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_expense, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        expenseViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}