package com.example.android_basic.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ViewModelProfile : BaseObservable() {
    // instantce cuar fire store
    val db = Firebase.firestore
    var  nextAction = MutableLiveData<String>()
    // id cua user
    val user = Firebase.auth.currentUser


    fun seen(data : String){

        if (user != null){
            val mdata = hashMapOf(
                user.uid to data
            )
            db.collection("User").document("${user.uid}+${Timestamp(Date())}")
                .set(mdata)
        }

        nextAction.postValue("Manager")
    }
}