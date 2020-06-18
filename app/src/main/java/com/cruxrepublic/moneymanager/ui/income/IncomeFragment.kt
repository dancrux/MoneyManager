package com.cruxrepublic.moneymanager.ui.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R

class IncomeFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        incomeViewModel =
                ViewModelProvider(this).get(IncomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_income, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        incomeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}