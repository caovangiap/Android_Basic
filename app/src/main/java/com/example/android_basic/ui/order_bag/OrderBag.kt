package com.example.android_basic.ui.order_bag

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentBagBinding
import com.example.android_basic.model.order.Bag
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct
import com.example.android_basic.viewmodel.ViewModelCheckOut
import java.text.NumberFormat


class OrderBag : Fragment() {

    lateinit var binding: FragmentBagBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelAllProduct
    lateinit var modelCheckOut : ViewModelCheckOut

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBagBinding.inflate(inflater, container, false)
        mView = binding.root
        viewModel = MainActivity.vMAllProduct
        modelCheckOut = MainActivity.vMCheckOut
        setUpView()
        return mView
    }

    fun setUpView() {
        // hien thi san pham
        showProduct()
        // toolbar
        setUpToolBar()

    }

    fun showProduct() {
        val data = viewModel.dataBag.getAll()
        val adapter = AdapterBag(data)
        adapter.SetClick(object : AdapterBag.EventClick{
            override fun Remove(position: Int) {

                viewModel.dataBag.delete(data[position])
                adapter.removeItem(position)
                Log.d("d",data.toString())
                // set tổng tiền
                allTotal(data)
            }
        })
        binding.AllItems.adapter = adapter
        binding.AllItems.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // set tổng tiền
        allTotal(data)
        // check out
        binding.Checkout.setOnClickListener {
            checkOut()
        }

    }

    fun setUpToolBar(){
        binding.myToolbar.inflateMenu(R.menu.toolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("DetailShoe")
        }
    }


    @SuppressLint("SetTextI18n")
    fun allTotal(data:MutableList<Bag>){

        var total = 0L
        for (i in 0 until data.size) {
            total += data[i].price.toLong()
        }
        // conver price dạng long về dạng tiền tệ
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0)
        val convert = numberFormat.format(total)
        // set text tổng tiền
        binding.Total.setText("Total :" + convert )
        modelCheckOut.total.postValue(convert)
    }

    // check out mua sản phẩm

    fun checkOut(){
        modelCheckOut.nextAction.postValue("CheckOut")
        // chuyen data tu gio hang sang model check out
        val data = viewModel.dataBag.getAll()
        for (i in 0 until data.size){
            modelCheckOut.data.add(data[i])
        }
    }



}