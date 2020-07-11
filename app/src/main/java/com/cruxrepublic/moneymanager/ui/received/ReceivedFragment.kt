package com.cruxrepublic.moneymanager.ui.received

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentIncomeBinding
import com.cruxrepublic.moneymanager.databinding.ReceivedFragmentBinding
import com.cruxrepublic.moneymanager.ui.income.IncomeAdapter
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModel
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ReceivedFragment : Fragment(), KodeinAware {

    companion object {
        fun newInstance() = ReceivedFragment()
    }

    private lateinit var receivedViewModel: ReceivedViewModel
    lateinit var binding: ReceivedFragmentBinding
    override val kodein by kodein()
    private val factory by instance<ReceivedViewModelFactory>()
    private val adapter = ReceivedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.received_fragment, container,
            false)
        receivedViewModel = ViewModelProvider(this, factory ).get(ReceivedViewModel::class.java)
        binding.receivedViewModel = receivedViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.receivedRecycler.adapter = adapter
        binding.receivedRecycler.layoutManager = LinearLayoutManager(this.activity)
        receivedViewModel.fetchReceived()
        receivedViewModel.receivedList.observe(viewLifecycleOwner, Observer {
            adapter.setReceived(it)
        })
    }
}