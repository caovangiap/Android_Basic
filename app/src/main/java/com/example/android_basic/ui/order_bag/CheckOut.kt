package com.example.android_basic.ui.order_bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentCheckoutBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelCheckOut

class CheckOut : Fragment() {

    lateinit var binding : FragmentCheckoutBinding
    lateinit var mView: View
    lateinit var viewModel : ViewModelCheckOut
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.vMCheckOut
        binding.viewmodel= viewModel
        setUpView()
        return mView
    }

    fun setUpView(){
        // cac cacn bao khi nhap sai du lieu
        viewModel.requiredName.observe(viewLifecycleOwner){
            binding.Name.helperText = it
        }
        viewModel.requiredAddress.observe(viewLifecycleOwner){
            binding.address.helperText =  it
        }
        viewModel.requiredPhone.observe(viewLifecycleOwner){
            binding.Phone.helperText = it
        }
        viewModel.requiredEmail.observe(viewLifecycleOwner){
            binding.Email.helperText = it
        }
        viewModel.total.observe(viewLifecycleOwner){
            binding.Total.text = it
        }
        setUpToolBar()
    }

    fun setUpToolBar(){
        binding.ToolBar.inflateMenu(R.menu.toolbar)
        binding.ToolBar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại
        binding.ToolBar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Bag")
        }
    }
}