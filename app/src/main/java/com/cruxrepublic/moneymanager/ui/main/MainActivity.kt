package com.cruxrepublic.moneymanager.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cruxrepublic.moneymanager.R
import com.cruxrepublic.moneymanager.databinding.ActivityMainBinding
import com.cruxrepublic.moneymanager.ui.utils.startLoginActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() , KodeinAware , NavigationView.OnNavigationItemSelectedListener{

    override val kodein by kodein()
    private val factory by instance<MainViewModelFactory>()
    private lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel

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
        //NavigationUI.setupWithNavController(navView, navController)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_income,
            R.id.navigation_expense,
            R.id.navigation_received,
            R.id.navigation_sent,
            R.id.navigation_profile
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_sign_out -> {
                Log.e("Main", "Logout Clicked")
                mainViewModel.logout()
                startLoginActivity()
                return false
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}