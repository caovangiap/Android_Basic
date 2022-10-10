package com.example.android_basic.ui.detail_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentDetailsizeBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct

class SelecSize : Fragment() {

    lateinit var binding : FragmentDetailsizeBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelAllProduct
    // lưu trữ các size
    val Size = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsizeBinding.inflate(inflater,container,false)
        mView= binding.root
        viewModel = MainActivity.vMAllProduct
        binding.viewModel = viewModel
        setUpView()
        return mView
    }

    fun setUpView() {
        binding.toolbar.inflateMenu(R.menu.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        selectSize()
    }

    // hiển thị view chọn size và set sự kiện chọn size
    fun selectSize(){
        // adaptersize
        for (i in 30..50) {
            Size.add(i.toString())
        }
        val adapter = AdapterDetailSize(Size)
        adapter.SetUp(object : AdapterDetailSize.ChooseSize {
            override fun Choose(position: Int) {
                viewModel.clickSize(Size[position])
                Log.d("clcik", "size")
            }
        })

        binding.DetailSize.adapter = adapter
        binding.DetailSize.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

}