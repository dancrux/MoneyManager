package com.cruxrepublic.moneymanager.ui.expense

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentAddExpenseDialogBinding
import com.cruxrepublic.moneymanager.ui.auth.AuthListener
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_income_dialog.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    AddExpenseDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class AddExpenseDialogFragment : BottomSheetDialogFragment(), AuthListener, KodeinAware {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var binding: FragmentAddExpenseDialogBinding
    override val kodein by kodein()
    private val factory by instance<ExpenseViewModelFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_expense_dialog, container,
            false)
        expenseViewModel = ViewModelProvider(this, factory).get(ExpenseViewModel::class.java)
        binding.expenseViewModel = expenseViewModel
        expenseViewModel.authListener = this
        return binding.root
    }

    override fun validateFields(): Boolean {
        binding.expenseReasonEdit.text.trim()
        binding.amountEditText.text.trim()
        if (binding.expenseReasonEdit.text.isNullOrEmpty()){
            binding.expenseReasonEdit.error = "Enter Reason For Expense"
            binding.expenseReasonEdit.requestFocus()
            return false

        }

        if (binding.amountEditText.text.isNullOrEmpty() ){
            binding.amountEditText.error = "Enter Amount"
            binding.amountEditText.requestFocus()
            return false
        }
        return true
    }

    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressBar.visibility = View.GONE
        Toast.makeText(context, "Expense Added Successfully", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    override fun notSuccessful() {
        progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }




//    companion object {
//
//        // TODO: Customize parameters
//        fun newInstance(itemCount: Int): AddExpenseDialogFragment =
//            AddExpenseDialogFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_ITEM_COUNT, itemCount)
//                }
//            }
//
//    }
}