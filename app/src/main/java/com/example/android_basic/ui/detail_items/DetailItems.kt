package com.example.android_basic.ui.detail_items

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.android_basic.R
import com.example.android_basic.databinding.DetailItemsBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct
import kotlinx.coroutines.MainScope
import java.text.NumberFormat

// màn chi tiết các sản phẩm
class DetailItems : Fragment() {

    lateinit var binding : DetailItemsBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelAllProduct

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailItemsBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.vMAllProduct
        binding.viewModel = viewModel
        setUpView()
        return mView
    }

    // set ui cho cac component trong view detail items
    fun setUpView(){
        // set image cho button 1 va 2
        viewModel.buttonDetail1.observe(viewLifecycleOwner){
            Glide.with(this)
                .load(it)
                .into(binding.Image1)
        }
       viewModel.buttonDetail2.observe(viewLifecycleOwner){
           Glide.with(this)
               .load(it)
               .into(binding.Image2)
       }
        // view cho image đầu trang
        viewModel.imageProduct.observe(viewLifecycleOwner){
            Glide.with(this)
                .load(it)
                .into(binding.viewFragment)
        }
        // set up toolbar
        setUpToolBar()
        // hien thi size san pham
        selecSize()
        // chuyen doi tien te
        changeMoney()

    }
    fun setUpToolBar(){
        binding.ToolBar.inflateMenu(R.menu.toolbar)
        binding.ToolBar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại
        binding.ToolBar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("ALL_PRODUCT")
            viewModel.sizeItems.postValue(null)
        }
    }
    // hien thi size san pham neu khach da chon
    fun selecSize(){
        binding.SelectSize.setOnClickListener {
            viewModel.nextAction.postValue("selecsize")
        }
        viewModel.sizeItems.observe(requireActivity()){
            if (it!=null){
                binding.SelectSize.text = it
            }
            else{
                binding.SelectSize.text = "SELECT SIZE"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun changeMoney(){
        var total = viewModel.dataItems.Price?.toLong()
        // conver price dạng long về dạng tiền tệ
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0)
        val convert = numberFormat.format(total)
        // set text tổng tiền
        binding.productPrice.setText(convert)
    }




}