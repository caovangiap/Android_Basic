package com.example.android_basic.ui.newproduct

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentNewproductBinding
import com.example.android_basic.databinding.HistoryBuyBinding
import com.example.android_basic.model.Product
import com.example.android_basic.ui.allitems.AdapterProduct
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelAllProduct
import com.example.android_basic.viewmodel.ViewModelNewProduct
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class NewProduct : Fragment() {

    lateinit var binding: FragmentNewproductBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelNewProduct
    lateinit var AllProduct: ViewModelAllProduct
    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewproductBinding.inflate(inflater, container, false)
        mView = binding.root
        viewModel = MainActivity.vMNewProduct
        AllProduct = MainActivity.vMAllProduct
        setUpToolBar()
        getData()
        return mView
    }

    fun setUpToolBar() {
        binding.myToolbar.inflateMenu(R.menu.toolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Manager")
        }
    }

    // gọi data hiển thị
    fun getData() {
        val data = mutableListOf<Product>()

        // lấy data từ firebase về
        db.collection("Product")
            .document("Shoe").collection("AllShoe")

            .whereEqualTo("new", "true")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in snapshot!!) {
                    doc.toObject(Product::class.java).let { data.add(it) }
                    Log.d("shoe",doc.toObject(Product::class.java).toString())
                }

                // đẩy data lên view
                val adapterShoe = AdapterProduct(data)
                adapterShoe.listener(object : AdapterProduct.Setonclick {
                    override fun onclick(position: Int) {
                        AllProduct.nextAction.postValue("DetailShoe")
                        Log.d("Shoe", "click")
                        // view model nhận position cua dataitems hien thi len detail
                        AllProduct.dataItems(data[position])
                    }
                })
                binding.product.adapter = adapterShoe
                binding.product.layoutManager = StaggeredGridLayoutManager(
                    2,
                    GridLayoutManager.VERTICAL
                )

            }

    }
}