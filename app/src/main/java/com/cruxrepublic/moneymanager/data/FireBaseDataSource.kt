package com.cruxrepublic.moneymanager.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cruxrepublic.moneymanager.data.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import com.google.firebase.database.*
import io.reactivex.Completable


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class FireBaseDataSource() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _result = MutableLiveData<java.lang.Exception?>()
    val result: LiveData<java.lang.Exception?> = _result


    private val _income = MutableLiveData<List<Income>>()
            val income: LiveData<List<Income>> = _income

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    private val _received = MutableLiveData<List<Received>>()
    val received: LiveData<List<Received>> = _received

    private val _sent = MutableLiveData<List<Sent>>()
    val sent: LiveData<List<Sent>> = _sent

    private val _profileFirstName = MutableLiveData<Any>()
    val profileFirstName: LiveData<Any> = _profileFirstName

    private val _profileSurName = MutableLiveData<Any>()
    val profileSurName: LiveData<Any> = _profileSurName

    private val _profileEmail = MutableLiveData<Any>()
    val profileEmail: LiveData<Any> = _profileEmail

    private val _profilePhoneNumber = MutableLiveData<Any>()
    val profilePhoneNumber: LiveData<Any> =_profilePhoneNumber

    private val _profileCountry = MutableLiveData<Any>()
    val profileCountry: LiveData<Any> = _profileCountry

    private val _profileAge = MutableLiveData<Any>()
    val profileAge: LiveData<Any> = _profileAge

    private val _profileGender = MutableLiveData<Any>()
    val profileGender: LiveData<Any>  = _profileGender

    private val _profileId = MutableLiveData<Any>()
    val profileId: LiveData<Any> = _profileId

    var email : String = ""
    var firstName: String = ""
    var surname: String = ""
    var country: String = ""
    var age: String = ""
    var phoneNumber = ""
    var sex = ""
    var id: String = ""

    private lateinit  var user: User



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
                        val id = firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
                        user = User( firstName, surname,id,email, phoneNumber, age, country, sex)

                        firebaseAuth.currentUser?.uid?.let {
                            firebaseDatabase.getReference("Users")
                                .child(id).child("user info").setValue(user).addOnCompleteListener { t ->
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
        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let {
            val dbIncome = firebaseDatabase.getReference("Users")
                    income.id = dbIncome.push().key.toString()
                dbIncome.child(accountId).child("income").child(income.id!!).setValue(income).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _result.value = null
                    } else
                        _result.value = it.exception
                }
        }
    }

    fun fetchIncome(){
        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(accountId).child("income") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _result.value = error.toException()
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

        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let {
            val dbExpense = firebaseDatabase.getReference("Users")
            expense.id = dbExpense.push().key.toString()
            dbExpense.child(accountId).child("expenses").child(expense.id!!).setValue(expense).addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else
                    _result.value = it.exception
            }
        }
    }
    fun fetchExpenses(){
        val accountId = firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(accountId).child("expenses") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _result.value = error.toException()
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

    fun sendMoney(sent: Sent){
                firebaseAuth.currentUser?.uid?.let {
                    val sendReference = firebaseDatabase.getReference("Users")
                     val sendId = sendReference.push().key.toString()
                  sendReference.child(sent.receiversId!!).child("received").child(sendId).setValue(sent)
                      .addOnCompleteListener {
                          if (it.isSuccessful) {
                              val receiversId = sendReference.push().key.toString()
                              sendReference.child(sent.sendersId).child("sent").child(receiversId).setValue(sent)
                              _result.value = null
                          } else
                              _result.value = it.exception
                       }
                }
            }

    fun getSentRecord(){
        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(accountId).child("sent") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _result.value = error.toException()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val allSent = mutableListOf<Sent>()
                    for (sentSnapshot in snapshot.children) {
                        val sent =sentSnapshot.getValue(Sent::class.java)
                       sent?.id = sentSnapshot.key.toString()
                       sent?.let { allSent.add(it) }
                    }
                    _sent.value = allSent

                }
            }
        })
    }
    fun getReceived(){
        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(accountId).child("received") }?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _result.value = error.toException()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val allReceived = mutableListOf<Received>()
                    for (incomeSnapshot in snapshot.children) {
                        val received = incomeSnapshot.getValue(Received::class.java)
                        received?.id = incomeSnapshot.key.toString()
                        received?.let { allReceived.add(it) }
                    }
                    _received.value = allReceived

                }
            }
        })
    }
    fun getProfile(){
//        userDetails = User( firstName, surname,id,email, phoneNumber, age, country, sex)
        val accountId= firebaseAuth.currentUser?.uid.toString().filter { it.isUpperCase() }
        firebaseAuth.currentUser?.uid?.let { firebaseDatabase.getReference("Users")
            .child(accountId).child("user info") }?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                _result.value = error.toException()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (profileSnapshot in snapshot.children){
                    when(profileSnapshot.key){
                        "firstName" -> _profileFirstName.value = profileSnapshot.value.toString()
                        "surname" ->   _profileSurName.value = profileSnapshot.value.toString()
                        "email" ->   _profileEmail.value = profileSnapshot.value.toString()
                        "phoneNumber" ->   _profilePhoneNumber.value = profileSnapshot.value.toString()
                        "country" ->   _profileCountry.value = profileSnapshot.value.toString()
                        "age" ->   _profileAge.value = profileSnapshot.value.toString()
                        "sex" ->   _profileGender.value = profileSnapshot.value.toString()
                        "id" ->   _profileId.value = profileSnapshot.value.toString()

                    }
//                      val  userSurname = snapshot.child("surname").value.toString()
//                       val userEmail = snapshot.child("email").value.toString()
//                       val userAge = snapshot.child("phoneNumber").value.toString()
//                       val userCountry = snapshot.child("country").value.toString()
//                       val userSex = snapshot.child("sex").value.toString()
//                        val userId = snapshot.child("id").value.toString()

                    }
                }


        })
}

    fun currentUser()= firebaseAuth.currentUser

}


