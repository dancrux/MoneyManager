package com.cruxrepublic.moneymanager.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cruxrepublic.moneymanager.data.model.Expense
import com.cruxrepublic.moneymanager.data.model.Income
import com.cruxrepublic.moneymanager.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class FireBaseDataSource() {
//    private val firebaseAuth: FirebaseAuth by lazy {
////        FirebaseAuth.getInstance()
////    }
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _result = MutableLiveData<java.lang.Exception?>()
    val result: LiveData<java.lang.Exception?>
        get() = _result

    private val _income = MutableLiveData<List<Income>>()
            val income: LiveData<List<Income>>
        get() = _income

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>>
        get() = _expenses
    var email : String = ""
    var firstName: String? = null
    var surname: String? = null
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var userid: String = firebaseAuth.currentUser?.uid.toString()
    var id = userid.filter { it.isDigit() }

    private lateinit  var user: User


//    fun login(email: String, password: String) {
//    firebaseAuth.signInWithEmailAndPassword(email,password)
//        .addOnCompleteListener {task->
//            if (task.isSuccessful)
//                task.isComplete
//
//            else task.exception
//
//    }
//
//    }
fun login(email: String, password: String) = Completable.create { emitter ->
    firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else emitter.onError(it.exception ?: Exception("An error occurred"))
            }
        }

}


//    fun login(email: String, password: String) = Completable.create {
//            emitter ->  firebaseAuth.signInWithEmailAndPassword(email,password)
//        .addOnCompleteListener {
//            if (!emitter.isDisposed){
//                if (it.isSuccessful)
////                it.isComplete
//                    emitter.onComplete()
//                else emitter.onError(it.exception!!)
//            }
//        }
//
//    }

//    fun register(email: String, password: String) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//
//                    if (it.isSuccessful) {
//                         user = User(firstName, surname, email,country, phoneNumber,sex,age)
//                        firebaseAuth.currentUser?.uid?.let {uid->
//                            firebaseDatabase.getReference("users")
//                                .child(uid).setValue(user).addOnCompleteListener {task->
//                                    if (task.isSuccessful) {
//                                        task.isComplete
//                                    } else
//                                       task.exception
//                                }
//
//                        }
//                    }
//
//                }
//
//            }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {
                        user = User( firstName, surname,id,email, phoneNumber, age, country, sex)

                        firebaseAuth.currentUser?.uid?.let {id->
                            firebaseDatabase.getReference("Users")
                                .child(id).child("user Info").setValue(user).addOnCompleteListener { t ->
                                    if (t.isSuccessful) {
                                        emitter.onComplete()
                                    } else
                                        emitter.onError(t.exception ?: Exception("An error occurred"))
                                }
                        }
                    } else {
                        emitter.onError(it.exception ?: Exception("An error occurred"))
                    }
                }

            }
    }


    fun logout() = firebaseAuth.signOut()

   fun checkIsNewUser(): Boolean{
       val metadata: FirebaseUserMetadata? = firebaseAuth.currentUser?.metadata
       return metadata?.creationTimestamp == metadata?.lastSignInTimestamp
   }

    fun addIncome(income: Income){
        firebaseAuth.currentUser?.uid?.let {uid->
            val dbIncome = firebaseDatabase.getReference("Users")
                    income.id = dbIncome.push().key.toString()
                dbIncome.child(uid).child("income").child(income.id!!).setValue(income).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _result.value = null
                    } else
                        _result.value = it.exception
                }
        }
    }

    fun fetchIncome(){
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(it).child("income") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val allIncome = mutableListOf<Income>()
                    for (incomeSnapshot in snapshot.children) {
                        val income = incomeSnapshot.getValue(Income::class.java)
                        income?.id = incomeSnapshot.key.toString()
                        income?.let { allIncome.add(it) }
                    }
                    _income.value = allIncome

                }
            }
        })
    }

    fun addExpense(expense: Expense){
        firebaseAuth.currentUser?.uid?.let {id->
            val dbExpense = firebaseDatabase.getReference("Users")
            expense.id = dbExpense.push().key.toString()
            dbExpense.child(id).child("expenses").child(expense.id!!).setValue(expense).addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else
                    _result.value = it.exception
            }
        }
    }
    fun fetchExpenses(){
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(it).child("expenses") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val allExpense = mutableListOf<Expense>()
                    for (incomeSnapshot in snapshot.children) {
                        val expense = incomeSnapshot.getValue(Expense::class.java)
                        expense?.id = incomeSnapshot.key.toString()
                        expense?.let { allExpense.add(it) }
                    }
                    _expenses.value = allExpense

                }
            }
        })
    }

    fun currentUser()= firebaseAuth.currentUser
}


