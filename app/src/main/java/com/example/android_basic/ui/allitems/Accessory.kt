package com.example.android_basic.ui.allitems

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.databinding.FragmentProductAccessoryBinding
import com.example.android_basic.model.Product
import com.example.android_basic.model.product.AccessoryData
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Accessory : Fragment() {
    lateinit var binding : FragmentProductAccessoryBinding
    lateinit var mView : View
    lateinit var viewModel: ViewModelAllProduct
    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductAccessoryBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.vMAllProduct
        viewProduct()
        return mView
    }


    // hiển thị toàn bộ sản phẩm
    fun viewProduct() {
        val data = mutableListOf<AccessoryData>()

        // lấy data từ firebase về
        db.collection("Product")
            .document("Accessory").collection("AllAccessory")

            .whereEqualTo("Condition", "true")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in snapshot!!) {
                    doc.toObject(AccessoryData::class.java).let { data.add(it) }
                    Log.d("accessory",doc.toObject(AccessoryData::class.java).toString())
                }

                // đẩy data lên view
                val adapterShoe = AdapterItems(data)
                adapterShoe.listener(object : AdapterItems.Setonclick {
                    override fun onclick(position: Int) {
                        Log.d("Shoe", "click")
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