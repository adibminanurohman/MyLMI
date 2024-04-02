package com.laznas.mylmi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laznas.mylmi.R
import com.laznas.mylmi.model.ResponseMagazinesItem

class MagazinesTerbaruAdapter (
    private val context: Context,
    private val dataList: ArrayList<ResponseMagazinesItem>
): RecyclerView.Adapter<MagazinesTerbaruAdapter.MyViewHolder>(){
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvTitleMagazines = view.findViewById<TextView>(R.id.tv_title_magazines)
        val tvYearMagazines = view.findViewById<TextView>(R.id.tv_year_magazines)
        val ivMagazines = view.findViewById<ImageView>(R.id.img_magazines)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description_magazines)
        val cvMgazines = view.findViewById<CardView>(R.id.cvMagazines)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_rv_e_majalah, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitleMagazines.text = dataList.get(position).title
        holder.tvYearMagazines.text = dataList.get(position).release
        holder.tvDescription.text = dataList.get(position).description

        // Load image using Glide
        Glide.with(context)
            .load(dataList.get(position).image)
            .placeholder(R.drawable.magazineplaceholder)
            .into(holder.ivMagazines)

        holder.cvMgazines.setOnClickListener {
            Toast.makeText(context, "" + dataList.get(position).title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<ResponseMagazinesItem>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}