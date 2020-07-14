package com.cruxrepublic.moneymanager.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.FragmentIncomeBinding
import com.cruxrepublic.moneymanager.databinding.ProfileFragmentBinding
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModel
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    lateinit var binding: ProfileFragmentBinding
    override val kodein by kodein()
    private val factory by instance<ProfileViewModelFactory>()
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment, container,
           false)
        profileViewModel = ViewModelProvider(this, factory). get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       profileViewModel.getUserProfile()
        binding.firstNameText.text = profileViewModel.firstName
        binding.surnameText.text = profileViewModel.surname
        binding.emailText.text = profileViewModel.email
        binding.ageText.text = profileViewModel.age
        binding.phoneText.text = profileViewModel.phoneNumber
        binding.countryText.text = profileViewModel.country
        binding.genderText.text = profileViewModel.gender
        binding.accountIdText.text = profileViewModel.id
    }

}