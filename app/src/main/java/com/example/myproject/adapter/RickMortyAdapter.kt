package com.example.myproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myproject.databinding.RecyclerViewCardBinding
import com.example.myproject.model.RickMorty

class RickMortyAdapter(private val context: Context, private val results:List<RickMorty>): RecyclerView.Adapter<RickMortyAdapter.MyViewHolder>(){

    private lateinit var myListener: OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(name:String,gender:String,status:String,type:String,species:String,originName:String,locationName:String,image:String)
    }
    fun setOnClickListener(listener: OnItemClickListener){
        myListener= listener
    }

    inner class MyViewHolder(val viewDataBinding: RecyclerViewCardBinding):RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= RecyclerViewCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result= results[position]
        holder.viewDataBinding.Name.text = result.name
        holder.viewDataBinding.Species.text = result.species
        Glide.with(context).load(result.image).into(holder.viewDataBinding.imageView)
        holder.itemView.setOnClickListener{
            myListener.onItemClick(result.name,result.gender,result.status,result.type,result.species,result.origin.name,result.location.name,result.image)
        }

    }

    override fun getItemCount(): Int {
        return results.size
    }


}
