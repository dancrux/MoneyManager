package com.cruxrepublic.moneymanager.ui.income

import android.app.Dialog
import android.os.Bundle
import android.system.Os.close
import android.util.Patterns
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.databinding.FragmentAddIncomeDialogBinding
import com.cruxrepublic.moneymanager.ui.auth.AuthListener
import com.cruxrepublic.moneymanager.ui.auth.AuthViewModelFactory
import com.cruxrepublic.moneymanager.ui.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_add_income_dialog.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

//// TODO: Customize parameter argument names
//const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    AddIncomeDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class EditIncomeDialogFragment : BottomSheetDialogFragment(),  KodeinAware {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var binding: FragmentAddIncomeDialogBinding
    override val kodein by kodein()
    private val factory by instance<IncomeViewModelFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_income_dialog, container,
            false)
        incomeViewModel = ViewModelProvider(this, factory).get(IncomeViewModel::class.java)
        binding.incomeViewModel = incomeViewModel
//        incomeViewModel.authListener = this

        val args: EditIncomeDialogFragmentArgs by navArgs()
        val source = args.sourceText
        val amount = args.amountText
        Toast.makeText(context, "Source: $source, Amount: $amount", Toast.LENGTH_SHORT).show()
//        incomeSourceEdit.setText(source)
//        amountEditText.setText(amount)
        return binding.root
    }


//    override fun validateFields(): Boolean {
//        binding.incomeSourceEdit.text.trim()
//        binding.amountEditText.text.trim()
//        if (binding.incomeSourceEdit.text.isNullOrEmpty()){
//            binding.incomeSourceEdit.error = "Enter Income Source"
//            binding.incomeSourceEdit.requestFocus()
//            return false
//
//        }
//
//        if (binding.amountEditText.text.isNullOrEmpty() ){
//            binding.amountEditText.error = "Enter Amount"
//            binding.amountEditText.requestFocus()
//            return false
//        }
//        return true
//    }
//
//    override fun onStarted() {
//        progressBar.visibility = View.VISIBLE
//    }
//
//    override fun onSuccess() {
//        progressBar.visibility = View.GONE
//        Toast.makeText(context, "Income Added Successfully", Toast.LENGTH_SHORT).show()
//        dismiss()
//    }
//
//    override fun notSuccessful() {
//        progressBar.visibility = View.GONE
//    }
//
//    override fun onFailure(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//
//    }

    //
//        override fun getItemCount(): Int {
//            return mItemCount
//        }
//    }
//

}