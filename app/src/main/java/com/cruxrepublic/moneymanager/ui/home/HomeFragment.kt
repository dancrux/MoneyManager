package com.cruxrepublic.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentExpenseBinding
import com.cruxrepublic.moneymanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel =homeViewModel
//        val textView: TextView = root.findViewById(R.id.totalFigureText)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }
}