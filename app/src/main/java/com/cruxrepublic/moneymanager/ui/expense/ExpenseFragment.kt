package com.cruxrepublic.moneymanager.ui.expense

import android.app.AlertDialog
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
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.databinding.FragmentExpenseBinding
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import com.cruxrepublic.moneymanager.ui.utils.ExpenseRecyclerClickListener
import com.google.android.gms.common.data.DataHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import kotlin.math.exp

class ExpenseFragment : Fragment(),ExpenseRecyclerClickListener, KodeinAware {

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

//        expenseViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.listener = this
        binding.expensesRecycler.adapter = adapter
        binding.expensesRecycler.layoutManager = LinearLayoutManager(this.activity)
        expenseViewModel.fetchExpenses()
        expenseViewModel.expenseList.observe(viewLifecycleOwner, Observer {
            adapter.setExpense(it)
        })
    }

    override fun onItemClicked(view: View, expense:Expense) {
        when(view.id){
            R.id.deleteImage ->{
                AlertDialog.Builder(requireContext()).also {
                    it.setTitle(getString(R.string.delete_confirmation))
                    it.setPositiveButton("Yes"){
                            dialog, which -> expenseViewModel.deleteExpense(expense)
                    }
                    it.setNegativeButton("No"){
                            dialog, which -> dialog.cancel()
                    }
                }.create().show()
            }
        }

    }
}