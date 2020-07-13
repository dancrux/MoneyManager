package com.cruxrepublic.moneymanager.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.preference.Preferences
import com.cruxrepublic.moneymanager.databinding.ActivityMainBinding
import com.cruxrepublic.moneymanager.ui.home.HomeFragmentDirections
import com.cruxrepublic.moneymanager.ui.income.AddIncomeDialogFragment
import com.cruxrepublic.moneymanager.ui.income.IncomeFragmentDirections
import com.cruxrepublic.moneymanager.ui.utils.startLoginActivity
import com.cruxrepublic.moneymanager.ui.utils.toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity() ,KodeinAware,MainInterface, NavigationView.OnNavigationItemSelectedListener{

    override val kodein by kodein()
    private val factory by instance<MainViewModelFactory>()
    private lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var repo: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        val firebaseDataSource= FireBaseDataSource()
//        val repository = UserRepository(firebaseDataSource)
//        val factory = MainViewModelFactory(repository  )
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel
        mainViewModel.mainInterface = this

        val toolbar: Toolbar = binding.container.toolbar
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout =binding.drawerLayout

        val navView: NavigationView = binding.navView
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        NavigationUI.setupWithNavController(toolbar, navController)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_income,
            R.id.navigation_expense,
            R.id.navigation_received,
            R.id.navigation_sent,
            R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_sign_out -> {
                mainViewModel.logout()
                startLoginActivity()
                return false
            }
            R.id.navigation_profile -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_home_to_navigation_profile)
            }
            R.id.navigation_home ->{
                findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showNewUserDialog()
//        val preferences = Preferences(this)
//       if (preferences.firstTimeLoginBoolean(sharedPref))
    }
    private fun showAddIncome(){
//        val view = layoutInflater.inflate(R.layout.fragment_add_income_dialog, null)
//        val dialog = BottomSheetDialog(this)
//        dialog.setContentView(view)
//        dialog.show()
       findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_home_to_navigation_add_income)
    }

    override fun promptNewUser() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome!")
        builder.setMessage("Add Your First Income")
        builder.setCancelable(true)

        builder.setPositiveButton("Add"){
                dialog, which ->  showAddIncome()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun promptOldUser(message: String) {
        toast(message)
    }
    fun showUserPrompt(){
        val isNewUser = Preferences(this).firstTimeLoginBoolean(sharedPref)
        if (isNewUser){
            mainViewModel.mainInterface.promptNewUser()
            Preferences(this).saveFirstTimeLoginBoolean(sharedPref, false)
        }else {
            mainViewModel.mainInterface.promptOldUser("Welcome Back")
        }
    }

}