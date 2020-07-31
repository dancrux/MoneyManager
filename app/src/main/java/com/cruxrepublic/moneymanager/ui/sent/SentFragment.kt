package com.cruxrepublic.moneymanager.ui.sent

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.Sent
import com.cruxrepublic.moneymanager.databinding.SentFragmentBinding
import com.cruxrepublic.moneymanager.ui.income.IncomeAdapter
import com.cruxrepublic.moneymanager.ui.utils.SentRecyclerClickListener
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SentFragment : Fragment(), SentRecyclerClickListener,KodeinAware {

    companion object {
        fun newInstance() = SentFragment()
    }

    private lateinit var sentViewModel: SentViewModel
    private lateinit var binding: SentFragmentBinding
    override val kodein by kodein()
    private val factory by instance<SentViewModelFactory>()
    private val adapter = SentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.sent_fragment, container, false)

        sentViewModel = ViewModelProvider(this, factory).get(SentViewModel::class.java)
        binding.sentViewModel = sentViewModel

        binding.sendFab.setOnClickListener {
           val action = SentFragmentDirections.actionNavigationSentToNavigationSendMoney()
            NavHostFragment.findNavController(this).navigate(action)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.listener = this
        binding.sentRecycler.adapter = adapter
        binding.sentRecycler.layoutManager = LinearLayoutManager(this.activity)
        sentViewModel.fetchSent()
        sentViewModel.sentList.observe(viewLifecycleOwner, Observer {
            adapter.setSent(it)
        })
    }

    override fun onItemClicked(view: View, sent: Sent) {
        when(view.id){
            R.id.deleteButton ->{
                AlertDialog.Builder(requireContext()).also {
                    it.setTitle(getString(R.string.delete_confirmation))
                    it.setPositiveButton("Yes"){
                            dialog, which ->sentViewModel.deleteSentItem(sent)
                    }
                    it.setNegativeButton("No"){
                            dialog, which -> dialog.cancel()
                    }
                }.create().show()
            }
        }

    }
}