package com.example.android_basic.viewmodel

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_basic.ui.login.FragmentLogin
import com.example.android_basic.utils.StorageLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ViewModelLogin : BaseObservable() {

    // chuyển màn
    var nextAction = MutableLiveData("LoginFalse")
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
    //
    lateinit var auth: FirebaseAuth

    // đăng nhập
    fun checkLogin(contextLogin: Activity){
//        val auth = Firebase.auth
//        if (!inputName.isEmpty() && !inputPass.isEmpty()){
//            auth.signInWithEmailAndPassword(inputName, inputPass)
//                .addOnCompleteListener( contextLogin ) { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(
//                            contextLogin, "success",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        // chuyển sang màn chính
//                        nextAction.postValue("LoginTrue")
//                    } else {
//
//                        Toast.makeText(
//                            contextLogin, "sai tên đăng nhập hoặc mật khẩu",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//        } else {
//            Toast.makeText(contextLogin, "Không được để trống email hoặc password",
//                Toast.LENGTH_SHORT).show()
//        }
        nextAction.postValue("LoginTrue")
    }
    // luu mat khau
    fun savePass(condition : Boolean, userName : String, passWord : String ){
        // true
        if (condition){
            StorageLogin.PutKey(StorageLogin.CheckSave,"TRUE")
            StorageLogin.PutKey(StorageLogin.UserName,userName)
            StorageLogin.PutKey(StorageLogin.Password,passWord)
        }
        // false -> xoa mat khau
        else{
            StorageLogin.Remove(StorageLogin.UserName)
            StorageLogin.Remove(StorageLogin.Password)
            StorageLogin.PutKey(StorageLogin.CheckSave, "FALSE")
        }
    }

    // quên mật khẩu
    fun ResetPassword(contextLogin: Activity, emailInput : String) {
        auth = Firebase.auth

        if (!emailInput.isEmpty()) {
            auth.sendPasswordResetEmail(emailInput)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val builder = AlertDialog.Builder(contextLogin)
                        builder.setTitle("success")
                        builder.setMessage("Check email và đổi mật khẩu")
                        builder.show()
                    }
                    else{
                        val builder = AlertDialog.Builder(contextLogin)
                        builder.setTitle("False")
                        builder.setMessage("email k tồn tại")
                        builder.show()
                    }
                }
        } else {
            val builder = AlertDialog.Builder(contextLogin)
            builder.setTitle("ERROR")
            builder.setMessage("XIN VUI LÒNG NHẬP EMAIL Ở TÊN TÀI KHOẢN")
            builder.show()
        }
    }
    fun singUp(){
        nextAction.postValue("SingUp")
    }

}