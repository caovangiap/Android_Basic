package com.example.android_basic.ui.history_buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic.R
import com.example.android_basic.databinding.FragmentCheckoutBinding
import com.example.android_basic.databinding.HistoryBuyBinding
import com.example.android_basic.ui.main.MainActivity
import com.example.android_basic.viewmodel.ViewModelHistoryBuy
import kotlinx.coroutines.*

class HistoryBuy : Fragment() {

    lateinit var binding: HistoryBuyBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelHistoryBuy
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HistoryBuyBinding.inflate(inflater, container, false)
        mView = binding.root
        viewModel = MainActivity.vMHistory
        // toolbar
        setUpToolBar()
        // call data về hien thi
        showHistory()
        return mView
    }
    fun setUpToolBar(){
        binding.myToolbar.inflateMenu(R.menu.toolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.ic_back)
//yêu cầu activity điều hướng quay trở lại
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Manager")
        }
    }

    // hien thị lịch sử mua bán
    fun showHistory() {
          viewModel.resetDataHistory()
        GlobalScope.launch {
            val calldata = GlobalScope.launch {
                viewModel.callData()
                delay(700)
            }
            calldata.join()
            withContext(Dispatchers.Main) {
                val adapter = AdapterHistory(viewModel.dataInformation, viewModel.dataItems)
                adapter.listener(object :AdapterHistory.click{
                    override fun clickRemove(position: Int) {
                        adapter.removeItem(position)
                    }
                })
                binding.HistoryBuy.adapter =
                    adapter
                binding.HistoryBuy.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.HistoryBuy.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }
}