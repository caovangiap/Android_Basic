package com.example.android_basic.ui.allitems

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.android_basic.R
import com.example.android_basic.model.Product
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

import java.text.NumberFormat

open class AdapterProduct(val data : MutableList<Product>) :
    RecyclerView.Adapter<AdapterProduct.ViewHodler>() {
    lateinit var onclick :Setonclick
    lateinit var context_1: Context


    interface Setonclick{
        fun onclick(position: Int){
        }
    }

    fun listener (listener: Setonclick){
        onclick = listener
    }

    class ViewHodler(items: View, click : Setonclick) : RecyclerView.ViewHolder(items)
    {
        val recycler: ViewPager2
        val name: TextView
        val price: TextView
        val indicator : DotsIndicator
        val Button : Button

        init {
            recycler = items.findViewById(R.id.recycler)
            name = items.findViewById(R.id.name)
            price = items.findViewById(R.id.price)
            indicator = items.findViewById(R.id.CircleIndicator)
            Button = items.findViewById(R.id.click)

            Button.setOnClickListener{
                click.onclick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_product, parent, false)
        context_1 = parent.context

        return ViewHodler(view,onclick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHodler, position: Int) {

        val data_items = mutableListOf<String>()
        data[position].Image.URL1.IG1?.let { data_items.add(it) }
        data[position].Image.URL1.IG2?.let { data_items.add(it) }
        data[position].Image.URL1.IG3?.let { data_items.add(it) }

        // conver price o dang long ve dang tien te
        val price = data[position].Price?.toLong()
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0);
        val convert = numberFormat.format(price)

        holder.recycler.adapter = items_shoe(data_items)
        holder.indicator.setViewPager2(holder.recycler)
        holder.price.setText(convert)
        holder.name.setText(data[position].Name)

    }
    override fun getItemCount(): Int {
        return data.size
    }

}


//các items shoe
open class items_shoe(val data: MutableList<String>) :
    RecyclerView.Adapter<items_shoe.ViewHolder>() {

    class ViewHolder(items: View) : RecyclerView.ViewHolder(items) {
        val Image_detail: ImageView

        init {
            Image_detail = items.findViewById(R.id.Image_detail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imagedetail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(data[position])
            .into(holder.Image_detail)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}






