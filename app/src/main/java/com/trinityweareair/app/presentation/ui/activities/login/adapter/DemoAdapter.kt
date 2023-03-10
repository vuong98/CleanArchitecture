package com.trinityweareair.app.presentation.ui.activities.login.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trinityweareair.app.databinding.ItemDataBinding
import com.trinityweareair.app.domain.model.PhotoItem

class DemoAdapter(private val inter : IDemo) : RecyclerView.Adapter<DemoAdapter.Companion.DemoViewHolder>() {

    companion object{
        class DemoViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root)
    }
    interface IDemo{
        fun getCount() : Int
        fun getData(position:Int) : PhotoItem
        fun getContext() : Context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
       val photo = inter.getData(position)
        holder.binding.txtId.text = photo.id.toString()
        holder.binding.txtIdAlbum.text = photo.albumId.toString()
        holder.binding.txtTitle.text = photo.title

        Glide.with(inter.getContext()).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg").into(holder.binding.imgUrl)
        Glide.with(inter.getContext()).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg").into(holder.binding.imgAlbum)

    }

    override fun getItemCount(): Int {
        return inter.getCount()
    }
}