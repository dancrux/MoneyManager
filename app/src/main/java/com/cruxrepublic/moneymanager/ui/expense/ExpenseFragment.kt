package com.cruxrepublic.moneymanager.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentExpenseBinding

class ExpenseFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var binding: FragmentExpenseBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_expense, container, false)

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        binding.expenseViewModel = expenseViewModel

        binding.addExpenseFab.setOnClickListener {
            val action = ExpenseFragmentDirections.actionNavigationExpenseToNavigationAddExpense()
            NavHostFragment.findNavController(this).navigate(action)
        }
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        expenseViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }
}