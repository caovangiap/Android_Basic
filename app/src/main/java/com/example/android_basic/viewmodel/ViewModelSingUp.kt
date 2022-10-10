package com.example.android_basic.viewmodel

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ViewModelSingUp : BaseObservable() {
    lateinit var auth : FirebaseAuth

    // biến để activity chuyển màn
    var nextAction = MutableLiveData("SingUp")
    // tên đăng nhập
    @get:Bindable
    var inputName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputName)
        }
    // mật khẩu
    @get:Bindable
    var inputPass: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputPass)
        }
    // nhắc lại mật khẩu
    @get:Bindable
    var inputPassAgain: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputPassAgain)
        }

    fun singUpNewAccout(context: Activity){
        auth = Firebase.auth
        if ( inputName.length>6 && inputPass.length>6  && inputPassAgain==inputPass ){
            auth.createUserWithEmailAndPassword(inputName, inputPass)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        nextAction.postValue("Login")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(context, "đăng kí thất bại ",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        if (!inputName.isEmpty() && !inputPass.isEmpty() && inputPassAgain!= inputPass ){
            Toast.makeText(context, "xin vui lòng nhập lại password trùng với password đã tạo ",
                Toast.LENGTH_SHORT).show()
        }
        if ( inputName.length< 6 && inputPass.length < 6 ){
            Toast.makeText(context, "mật khẩu phải lớn hơn 6 kí tự",
                Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Không được để trống email hoặc password",
                Toast.LENGTH_SHORT).show()
        }
    }
}