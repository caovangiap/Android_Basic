package com.example.android_basic.ui.allitems

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.databinding.FragmentProductShoeBinding
import com.example.android_basic.model.Product
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Shoe : Fragment() {
    lateinit var binding: FragmentProductShoeBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelAllProduct
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductShoeBinding.inflate(inflater, container, false)
        viewModel = MainActivity.vMAllProduct
        mView = binding.root
        setUpView()
        return mView
    }

    fun setUpView() {
        viewProduct()
    }

    // hiển thị toàn bộ sản phẩm
    fun viewProduct() {
        val data = mutableListOf<Product>()

        // lấy data từ firebase về
        db.collection("Product")
            .document("Shoe").collection("AllShoe")

            .whereEqualTo("Size", "30")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in snapshot!!) {
                    doc.toObject(Product::class.java).let { data.add(it) }
                    Log.d("shoe",doc.toString())
                }

                // đẩy data lên view
                val adapterShoe = AdapterProduct(data)
                adapterShoe.listener(object : AdapterProduct.Setonclick {
                    override fun onclick(position: Int) {
                        viewModel.nextAction.postValue("DetailShoe")
                        Log.d("Shoe", "click")
                        // view model nhận position cua dataitems hien thi len detail
                        viewModel.dataItems(data[position])
                    }
                })
                binding.viewItem.adapter = adapterShoe
                binding.viewItem.layoutManager = StaggeredGridLayoutManager(
                    2,
                    GridLayoutManager.VERTICAL
                )

            }

    }



}
