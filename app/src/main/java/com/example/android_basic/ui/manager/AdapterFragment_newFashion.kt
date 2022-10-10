package com.example.ttcs_shoppinng_mall.ui.adapter.fragmentmain


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_basic.R

import com.example.ttcs_shoppinng_mall.data.model.NewFashion



class AdapterFragment_newFashion(val data: MutableList<NewFashion>)  :
    RecyclerView.Adapter<AdapterFragment_newFashion.viewholder>()  {


    interface  SetOnClick {
        fun Onclick(position: Int)
    }
    lateinit var onclick : SetOnClick

    fun EventOnclick(click: SetOnClick) {
        onclick = click
    }
    class viewholder(itemView: View, click: SetOnClick) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val textView: TextView
        val button : Button

        init {
            imageView = itemView.findViewById(R.id.image_thisweekhighlights)
            textView = itemView.findViewById(R.id.text_thisweekhighlights)
            button = itemView.findViewById(R.id.button_thisweekhighlight)
            button.setOnClickListener {
                click.Onclick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_fragment_newfashion, parent, false)
        return viewholder(view,onclick)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {


        holder.textView.setText(data[position].Titel)
        Glide.with(holder.itemView.context)
            .load(data[position].Image)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}