package com.example.genshinimpact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinimpact.databinding.ListContentBinding
import com.example.genshinimpact.entry.SpecificInfo

class ListAdapter(private val mSpecificInfo: ArrayList<SpecificInfo>) :

    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(listContentBinding: ListContentBinding) :
        RecyclerView.ViewHolder(listContentBinding.root) {
        val starName: TextView = listContentBinding.tvCharacterName
        val starNum: TextView = listContentBinding.pbCharacterNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.starName.text = mSpecificInfo[position].name
        holder.starNum.text = mSpecificInfo[position].count
    }

    override fun getItemCount() = mSpecificInfo.size
}