package com.cruxrepublic.moneymanager.ui.sent

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.SentFragmentBinding

class SentFragment : Fragment() {

    companion object {
        fun newInstance() = SentFragment()
    }

    private lateinit var sentViewModel: SentViewModel
    private lateinit var binding: SentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.sent_fragment, container, false)

        sentViewModel = ViewModelProvider(this).get(SentViewModel::class.java)
        binding.sentViewModel = sentViewModel

        binding.sendFab.setOnClickListener {
           val action = SentFragmentDirections.actionNavigationSentToNavigationSendMoney()
            NavHostFragment.findNavController(this).navigate(action)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sentViewModel = ViewModelProvider(this).get(SentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}