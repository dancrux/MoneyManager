package com.cruxrepublic.moneymanager.app

import android.app.Application
import com.cruxrepublic.moneymanager.data.firebase.FireBaseDataSource
import com.cruxrepublic.moneymanager.data.UserRepository
import com.cruxrepublic.moneymanager.data.firebase.notification.FirebaseService
import com.cruxrepublic.moneymanager.data.preference.Preferences
import com.cruxrepublic.moneymanager.ui.auth.AuthViewModelFactory
import com.cruxrepublic.moneymanager.ui.expense.ExpenseViewModelFactory
import com.cruxrepublic.moneymanager.ui.income.IncomeViewModelFactory
import com.cruxrepublic.moneymanager.ui.main.MainViewModelFactory
import com.cruxrepublic.moneymanager.ui.profile.ProfileViewModelFactory
import com.cruxrepublic.moneymanager.ui.received.ReceivedViewModelFactory
import com.cruxrepublic.moneymanager.ui.sent.SentViewModelFactory
import com.google.firebase.database.FirebaseDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MoneyManagerApplication: Application(), KodeinAware {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MoneyManagerApplication))
        bind() from singleton { FireBaseDataSource() }
        bind() from singleton { UserRepository(instance())}
        bind() from singleton { Preferences(instance())}
        bind() from provider { AuthViewModelFactory(instance(), instance())}
        bind() from provider { MainViewModelFactory(instance(), instance()) }
        bind() from provider { IncomeViewModelFactory(instance()) }
        bind() from provider { ExpenseViewModelFactory(instance()) }
        bind() from provider { SentViewModelFactory(instance()) }
        bind() from provider { ReceivedViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }
}