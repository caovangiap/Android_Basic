package com.example.android_basic.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic.databinding.FragmentProfileBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelProfile

class FragmentProfile : Fragment() {

    lateinit var binding : FragmentProfileBinding
    lateinit var mView: View
    lateinit var ViewModel : ViewModelProfile
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        mView = binding.root
        ViewModel = MainActivity.vMProfile
        binding.viewmodel = ViewModel
        seen()
        return mView
    }
    fun seen(){
        binding.Seen.setOnClickListener {
            ViewModel.seen(binding.InputEmail.text.toString())
        }
    }

}