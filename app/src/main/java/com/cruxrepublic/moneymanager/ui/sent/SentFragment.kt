package com.cruxrepublic.moneymanager.ui.sent

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R

class SentFragment : Fragment() {

    companion object {
        fun newInstance() = SentFragment()
    }

    private lateinit var viewModel: SentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sent_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}