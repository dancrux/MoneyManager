package com.cruxrepublic.moneymanager.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.model.User
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
       showProfileInfo()
    }
    private fun showProfileInfo(){
        profileViewModel.getUserProfile()
        profileViewModel.firstName.observe(viewLifecycleOwner, Observer {
            binding.firstNameText.text = it.toString()
        })
        profileViewModel.surname.observe(viewLifecycleOwner, Observer {
            binding.surnameText.text = it.toString()
        })
        profileViewModel.email.observe(viewLifecycleOwner, Observer {
            binding.emailText.text = it.toString()
        })
        profileViewModel.id.observe(viewLifecycleOwner, Observer {
            binding.accountIdText.text = it.toString()
        })
        profileViewModel.phoneNumber.observe(viewLifecycleOwner, Observer {
            binding.phoneText.text = it.toString()
        })
        profileViewModel.country.observe(viewLifecycleOwner, Observer {
            binding.countryText.text = it.toString()
        })
        profileViewModel.age.observe(viewLifecycleOwner, Observer {
            binding.ageText.text = it.toString()
        })
        profileViewModel.gender.observe(viewLifecycleOwner, Observer {
            binding.genderText.text = it.toString()
        })

    }
}