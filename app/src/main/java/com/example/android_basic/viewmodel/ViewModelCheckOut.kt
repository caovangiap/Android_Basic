package com.example.android_basic.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.example.android_basic.model.checkbuy.DecideBuy
import com.example.android_basic.model.order.Bag
import com.example.android_basic.ui.main.MainActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.regex.Pattern


class ViewModelCheckOut : BaseObservable() {

    var nextAction = MutableLiveData<String>()

    // Name Input
    @get:Bindable
    var inputName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputName)
        }

    // Phone Input
    @get:Bindable
    var inputPhone: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputPhone)
        }

    // input email
    @get:Bindable
    var inputEmail: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputEmail)
        }

    // input address
    @get:Bindable
    var inputAddress: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputAddress)
        }

    // total
    var total = MutableLiveData<String>()

    // request
    var requiredName = MutableLiveData<String>()
    var requiredPhone = MutableLiveData<String>()
    var requiredEmail = MutableLiveData<String>()
    var requiredAddress = MutableLiveData<String>()

    // condition
    var conditionName = false
    var conditionEmail = false
    var conditionAddress = false
    var conditionPhone = false

    // data don hang
    var data = arrayListOf<Bag>()

    // instantce cuar fire store
    val db = Firebase.firestore

    // id cua user
    val user = Firebase.auth.currentUser

    fun checkOut() {
        checkName()
        checkPhone()
        checkEmail()
        checkAddress()
        if (conditionName && conditionAddress && conditionEmail && conditionPhone) {
            successCheckOut()
        }
    }


    // ham check dieu kien sđt
    fun mobiValidate(input: String): Boolean {
        val p = Pattern.compile("[0][0-9]{9}")
        val m = p.matcher(input)
        return m.matches()
    }


    // check name
    fun checkName() {
        if (inputName == "") {
            requiredName.postValue("không được để trống trường này ")
        } else {
            requiredName.postValue("success")
            conditionName = true
        }
    }

    // check Phone
    fun checkPhone() {
        if (!mobiValidate(inputPhone)) {
            requiredPhone.postValue("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ ")
        } else {
            requiredPhone.postValue("success")
            conditionPhone = true
        }
    }

    // check email
    fun checkEmail() {
        if (inputEmail == "") {
            requiredEmail.postValue("khong co chu thich gi ?")
            conditionEmail = true
        } else {
            requiredEmail.postValue("success")
            conditionEmail = true
        }
    }

    // check address
    fun checkAddress() {
        if (inputAddress == "") {
            requiredAddress.postValue("không được để trống trường này ")
        } else {
            requiredAddress.postValue("success")
            conditionAddress = true
        }
    }

    // chốt đơn hàng khi đã đủ thông tin

    fun successCheckOut() {

        // chuyen doi time dang second sang string
        val time = converTime(Timestamp(Date()).seconds)
        // đẩy đơn hàng lên fire base
        if (user != null && total.value != null) {
            // thoong tin nguoi nhan

            val decidedBuy = DecideBuy(
                inputName, inputAddress, inputEmail, inputPhone, time, user.uid,
                total.value!!,"False"
            )
            db.collection("Order").document("${user.uid}+$time")
                .set(decidedBuy, SetOptions.merge())

            // thong tin san pham
            for (i in 0 until data.size) {
                db.collection("Order").document("${user.uid}+$time")
                    .collection("product")
                    .document("${data[i].name_Product} Size: ${data[i].size}")
                    .set(data[i], SetOptions.merge())
            }
        }
        // chuyên đơn hàng sang lịch sử
        nextAction.postValue("Manager")
        // xoa tat ca du lieu don hang ve rong lan tiep theo gio hang se trong
        resetData()
    }

    fun converTime(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val second = seconds % 60
        val timeString = String.format("%02d:%02d:%02d", hours, minutes, second)
        return timeString
    }

    fun resetData(){
        MainActivity.vMAllProduct.dataBag.DeleteAll(MainActivity.vMAllProduct.dataBag.getAll())
    }
}