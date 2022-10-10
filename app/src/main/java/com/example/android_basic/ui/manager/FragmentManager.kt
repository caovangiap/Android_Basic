package com.example.android_basic.ui.manager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentManagerBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelManager
import com.example.ttcs_shoppinng_mall.ui.adapter.fragmentmain.AdapterFragment_newFashion

class FragmentManager : Fragment() {

    lateinit var binding : FragmentManagerBinding
    lateinit var viewModel : ViewModelManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManagerBinding.inflate(inflater,container,false)
        viewModel = MainActivity.vMManager
        binding.viewmodel = viewModel
        val mView = binding.root
        // danh sach cac bai bao thoi trang
        newFashion()
        setUpToolBar()
        return mView
    }

    // hien thi cac bai bao thoi trang
    fun newFashion(){
        viewModel.recycler_NewFashion(requireActivity())
        viewModel.LiveDataNewFashion.observe(requireActivity()){
            val SetEventItems = AdapterFragment_newFashion(it)
            SetEventItems.EventOnclick(object : AdapterFragment_newFashion.SetOnClick{
                override fun Onclick(position: Int) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(it[position].URL)
                    }
                    startActivity(intent)
                }
            })
            binding.newFashion.adapter = SetEventItems
            binding.newFashion.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
        // hien thi lich sử mua hang
        binding.HistoryBuy.setOnClickListener {
            viewModel.history()
            Log.d("","click")
        }
    }

    // set event cho toolbar
    fun setUpToolBar(){
        binding.myToolbar.inflateMenu(R.menu.toolbar_manager)
        binding.myToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.bag ->{
                    viewModel.nextAction.postValue("CheckOut")
                    true
                }
                R.id.profile->{
                    viewModel.nextAction.postValue("Login")
                    true
                }
                else -> false
            }
        }
    }


}