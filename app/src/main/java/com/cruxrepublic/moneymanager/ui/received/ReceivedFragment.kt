package com.cruxrepublic.moneymanager.ui.received

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R

class ReceivedFragment : Fragment() {

    companion object {
        fun newInstance() = ReceivedFragment()
    }

    private lateinit var viewModel: ReceivedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.received_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReceivedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}