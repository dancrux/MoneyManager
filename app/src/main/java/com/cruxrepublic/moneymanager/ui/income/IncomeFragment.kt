package com.cruxrepublic.moneymanager.ui.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.databinding.FragmentIncomeBinding
import com.cruxrepublic.moneymanager.ui.utils.IncomeRecyclerClickListener
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class IncomeFragment : Fragment(), KodeinAware, IncomeRecyclerClickListener {

    private lateinit var incomeViewModel: IncomeViewModel
    lateinit var binding: FragmentIncomeBinding
    override val kodein by kodein()
    private val factory by instance<IncomeViewModelFactory>()
    private val adapter = IncomeAdapter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_income, container, false
        )
        incomeViewModel = ViewModelProvider(this, factory).get(IncomeViewModel::class.java)
        binding.incomeViewModel = incomeViewModel



        binding.addIncomeFab.setOnClickListener {
            val action = IncomeFragmentDirections.actionNavigationIncomeToNavigationAddIncome()
            NavHostFragment.findNavController(this).navigate(action)
            //            val view = layoutInflater.inflate(R.layout.fragment_add_income_dialog, null)
//            val dialog = activity?.let { it1 -> BottomSheetDialog(it1) }
//            dialog?.setContentView(view)
//            dialog?.show()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.listener = this
        binding.incomeRecycler.adapter = adapter
        binding.incomeRecycler.layoutManager = LinearLayoutManager(this.activity)
        incomeViewModel.fetchIncome()
        incomeViewModel.incomeList.observe(viewLifecycleOwner, Observer {
            adapter.setIncome(it)
        })
    }

    override fun onItemClicked(view: View, income: Income) {
        when(view.id){
            R.id.editImage ->{
//                EditIncomeDialogFragment(income).show(childFragmentManager, "")
                openEditIncome(income)
            }
            R.id.deleteImage ->{

            }
        }
    }
    private fun openEditIncome(income: Income){
//        val incomeSource = sourceText.text.toString()
//        val amount = amountText.text.toString()
                    val action = IncomeFragmentDirections.actionNavigationIncomeToNavigationEditIncome(
                    income.sourceOfIncome!!, income.amount!!)
                findNavController().navigate(action)

    }
}