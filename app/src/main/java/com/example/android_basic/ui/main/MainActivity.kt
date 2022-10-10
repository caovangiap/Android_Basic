package com.example.android_basic.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android_basic.R
import com.example.android_basic.databinding.ActivityMainBinding
import com.example.android_basic.ui.allitems.FragmentAllProduct
import com.example.android_basic.ui.detail_items.DetailItems
import com.example.android_basic.ui.detail_items.SelecSize
import com.example.android_basic.ui.history_buy.HistoryBuy
import com.example.android_basic.ui.manager.FragmentManager
import com.example.android_basic.ui.login.FragmentLogin
import com.example.android_basic.ui.login.FragmentSingUp
import com.example.android_basic.ui.newproduct.NewProduct
import com.example.android_basic.ui.order_bag.CheckOut
import com.example.android_basic.ui.order_bag.OrderBag
import com.example.android_basic.ui.profile.FragmentProfile
import com.example.android_basic.viewmodel.*

class MainActivity : AppCompatActivity() {

    lateinit var binding  : ActivityMainBinding
    lateinit var mView: View
    companion object{
        lateinit var ApplicationContext : Context
        lateinit var vMlLogin : ViewModelLogin
        lateinit var vMSingUp : ViewModelSingUp
        lateinit var vMManager : ViewModelManager
        lateinit var vMAllProduct: ViewModelAllProduct
        lateinit var vMCheckOut :ViewModelCheckOut
        lateinit var vMHistory : ViewModelHistoryBuy
        lateinit var vMNewProduct : ViewModelNewProduct
        lateinit var vMProfile : ViewModelProfile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mView = binding.root
        ApplicationContext = this
        setContentView(mView)
        // modelview đăng nhập
        vMlLogin = ViewModelLogin()
        // modelview đăng kí
        vMSingUp = ViewModelSingUp()
        // modelview manager dieu huong cac chuc nang
        vMManager = ViewModelManager()
        // viewmodel hiển thị sản phẩm và thông tin khách hàng
        vMAllProduct = ViewModelAllProduct()
        // check out đơn hangf
        vMCheckOut = ViewModelCheckOut()
        // history
        vMHistory = ViewModelHistoryBuy()
        // new product
        vMNewProduct = ViewModelNewProduct()
        // canh bao
        vMProfile = ViewModelProfile()

        // chức năng đăng nhâp
        CheckLogin()
        // chức năng đăng kí
        SingUp()
        // dieu huong cac chuc nang
        Manager()
        // chức năng hiển thị sản phẩm + chốt đơn
        allProduct()
        // chức năng check out đơn hàng
        checkOut()
        // chức năng lịch sử đơn hàng
        history()
        // chuc nang cua man product
        newProduct()
        // chuc nang cua man gui canh bao
        profile()

    }

    // login
    private fun CheckLogin(){

        vMlLogin.checkLogin(this)

        vMlLogin.nextAction.observe(this){
            when(it){
                "LoginFalse"->{
                    val fragment = FragmentLogin()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "LoginTrue"->{
                    val fragment = FragmentManager()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "SingUp"->{
                    val fragment = FragmentSingUp()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }
    private fun SingUp(){
        // chuyển sang màn đăng nhập khi đăng kí thành công
        vMSingUp.nextAction.observe(this){
            when(it){
                "Login"->{
                    val fragment = FragmentLogin()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }
    // dieu huong cac chưc năng chính
    fun Manager(){
        vMManager.nextAction.observe(this){
            when(it){
                "ALL_PRODUCT" ->{
                    val fragment = FragmentAllProduct()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "CheckOut"->{
                    val fragment = OrderBag()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "HistoryBuy"->{
                    val fragment = HistoryBuy()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "NewProduct"->{
                    val fragment = NewProduct()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "Login"->{
                    val fragment = FragmentLogin()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "profile"->{
                    val fragment = FragmentProfile()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }

    fun allProduct(){
        vMAllProduct.nextAction.observe(this){
            when(it){
                "DetailShoe"->{
                    val fragment = DetailItems()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "selecsize"->{
                    val fragment = SelecSize()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "Bag"->{
                    val fragment = OrderBag()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "ALL_PRODUCT" ->{
                    val fragment = FragmentAllProduct()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.add(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }
    // check out thông tin và đẩy lên firbase

    fun checkOut(){
        vMCheckOut.nextAction.observe(this){
            when(it){
                "CheckOut"->{
                    val fragment = CheckOut()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "Bag"->{
                    val fragment = OrderBag()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
                "Manager" ->{
                    val fragment = FragmentManager()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }

    fun history(){
        vMHistory.nextAction.observe(this){
            when(it){
                "Manager" ->{
                    val fragment = FragmentManager()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }
    fun newProduct(){
        vMNewProduct.nextAction.observe(this){
            when(it){
                "Manager" ->{
                    val fragment = FragmentManager()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }


            }
        }
    }
    fun profile(){
        vMProfile.nextAction.observe(this){
            when(it){
                "Manager" ->{
                    val fragment = FragmentManager()
                    val managerActivity = supportFragmentManager.beginTransaction()
                    managerActivity.replace(R.id.view,fragment)
                    managerActivity.addToBackStack(null)
                    managerActivity.commit()
                }
            }
        }
    }


}