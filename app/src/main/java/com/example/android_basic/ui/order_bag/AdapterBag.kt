package com.example.android_basic.ui.order_bag

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_basic.R
import com.example.android_basic.model.order.Bag
import java.text.NumberFormat


class AdapterBag ( val data: MutableList<Bag>) : RecyclerView.Adapter<AdapterBag.ViewHolder>() {

    lateinit var Remove : EventClick
    interface EventClick{
        fun Remove(position: Int)
    }
    fun SetClick(Click: EventClick){
        Remove = Click
    }

    class ViewHolder(itemView: View, Remove: EventClick): RecyclerView.ViewHolder(itemView) {
        val Image : ImageView
        val Name : TextView
        val Price : TextView
        val Size : TextView
        val Number : TextView
        val Button : Button

        init {
            Image = itemView.findViewById(R.id.Image_Items)
            Name = itemView.findViewById(R.id.NameProduct)
            Price = itemView.findViewById(R.id.PriceProduct)
            Size = itemView.findViewById(R.id.SizeProduct)
            Number = itemView.findViewById(R.id.NumberProduct)
            Button = itemView.findViewById(R.id.Remove)

            Button.setOnClickListener {
                Remove.Remove(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_bag, parent, false)
        return ViewHolder(view,Remove)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Name.setText(data[position].name_Product)
        holder.Size.setText(data[position].size)

        // conver dạng long thành tiền tệ
        val price = data[position].price.toLong()
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.setMaximumFractionDigits(0);
        val convert = numberFormat.format(price)
        //

        holder.Price.setText(convert)
        Glide.with(holder.itemView.context)
            .load(data[position].image)
            .into(holder.Image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun removeItem(position: Int) {
        data.remove(data[position])
        notifyItemRemoved(position)
        Log.d("Adapter",position.toString())
    }
}