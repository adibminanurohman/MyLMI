package com.laznas.mylmi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.laznas.mylmi.R
import com.laznas.mylmi.model.ResponseMagazinesItem

class MagazinesAdapter (
    private val context: Context,
    private val dataList: ArrayList<ResponseMagazinesItem>
): RecyclerView.Adapter<MagazinesAdapter.MyViewHolder>(){
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvTitleMagazines = view.findViewById<TextView>(R.id.tv_title_magazines)
        val tvYearMagazines = view.findViewById<TextView>(R.id.tv_year_magazines)
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