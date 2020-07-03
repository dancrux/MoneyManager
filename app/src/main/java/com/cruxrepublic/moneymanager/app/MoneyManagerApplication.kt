package com.cruxrepublic.moneymanager.app

import android.app.Application
import com.cruxrepublic.moneymanager.data.FireBaseDataSource
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.ui.auth.AuthListener
import com.cruxrepublic.moneymanager.ui.auth.AuthViewModelFactory
import com.cruxrepublic.moneymanager.ui.expense.ExpenseViewModelFactory
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import com.cruxrepublic.moneymanager.ui.main.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MoneyManagerApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MoneyManagerApplication))


        bind() from singleton { FireBaseDataSource()}
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance())}
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { IncomeViewModelFactory(instance()) }
        bind() from provider { ExpenseViewModelFactory(instance()) }

    }
}