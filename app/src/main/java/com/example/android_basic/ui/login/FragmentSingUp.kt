package com.example.android_basic.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic.databinding.FragmentSingupBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelSingUp

class FragmentSingUp : Fragment() {

    lateinit var binding : FragmentSingupBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelSingUp
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = MainActivity.vMSingUp
        binding = FragmentSingupBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        mView = binding.root
        function()
        return mView
    }

    fun function(){
        // chức năng tạo tài khoản mới
        binding.SingUp.setOnClickListener {
            viewModel.singUpNewAccout(requireActivity())
        }
        binding.backtoLongin.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}