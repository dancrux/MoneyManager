package com.cruxrepublic.moneymanager.ui.income

import android.content.Context
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
import com.cruxrepublic.moneymanager.databinding.FragmentIncomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class IncomeFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel
    lateinit var binding: FragmentIncomeBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_income, container, false
        )
        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)
        binding.incomeViewModel = incomeViewModel
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        incomeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        binding.addIncomeFab.setOnClickListener {

            val action = IncomeFragmentDirections.actionNavigationIncomeToNavigationAddIncome()
            NavHostFragment.findNavController(this).navigate(action)

        }
        return binding.root
    }
}