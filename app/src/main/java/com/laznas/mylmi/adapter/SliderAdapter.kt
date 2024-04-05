package com.laznas.mylmi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznas.mylmi.R
import com.laznas.mylmi.model.ResponseFundraisingsItem

class SliderAdapter (
    private val context: Context,
    private val dataList: ArrayList<ResponseFundraisingsItem>
): RecyclerView.Adapter<SliderAdapter.MyViewHolder>(){
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val ivSlider = view.findViewById<ImageView>(R.id.iv_slider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_rv_slider, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // Load image using Glide
        Glide.with(context)
            .load(dataList.get(position).image)
            .placeholder(R.drawable.magazineplaceholder)
            .into(holder.ivSlider)

    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<ResponseFundraisingsItem>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}