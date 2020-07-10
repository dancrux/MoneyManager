package com.cruxrepublic.moneymanager.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentExpenseBinding
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ExpenseFragment : Fragment(), KodeinAware {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var binding: FragmentExpenseBinding
    override val kodein by kodein()
    private val factory by instance<ExpenseViewModelFactory>()
    private val adapter = ExpensesAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_expense, container, false)

        expenseViewModel = ViewModelProvider(this, factory).get(ExpenseViewModel::class.java)
        binding.expenseViewModel = expenseViewModel

        binding.addExpenseFab.setOnClickListener {
            val action = ExpenseFragmentDirections.actionNavigationExpenseToNavigationAddExpense()
            NavHostFragment.findNavController(this).navigate(action)
//            findNavController().navigate(R.id.action_navigation_expense_to_navigation_add_expense)
        }


//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        expenseViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.expensesRecycler.adapter = adapter
        binding.expensesRecycler.layoutManager = LinearLayoutManager(this.activity)
        expenseViewModel.fetchExpenses()
        expenseViewModel.expenseList.observe(viewLifecycleOwner, Observer {
            adapter.setExpense(it)
        })
    }
}