package com.example.android_basic.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic.databinding.FragmentLoginBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelLogin

class FragmentLogin : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var mView: View
    var viewModel = MainActivity.vMlLogin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        mView = binding.root
        binding.viewModel = viewModel
        funticionOfButton()
        return mView
    }

    fun funticionOfButton() {
        // xét sự kiện đăng nhập
        binding.SingIn.setOnClickListener {
            viewModel.checkLogin(requireActivity())
        }
        // xét sự kiện lưu mật khẩu
        binding.checkbox.setOnClickListener {
            viewModel.savePass(
                binding.checkbox.isChecked,
                binding.UserName.text.toString(),
                binding.UserPassword.text.toString()
            )
        }
        // xét sự kiện quên mật khẩu
        binding.Forgot.setOnClickListener {
            viewModel.ResetPassword(requireActivity(),binding.UserName.text.toString())

        }
    }

}