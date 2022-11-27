package com.example.genshinimpact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinimpact.databinding.RoleAnalysisItemBinding
import com.example.genshinimpact.num
import com.example.genshinimpact.role

class RoleAnaylAdapter(val list: ArrayList<String>) :
    RecyclerView.Adapter<RoleAnaylAdapter.ViewHolder>() {
    class ViewHolder(roleAnalysisItemBinding: RoleAnalysisItemBinding) :
        RecyclerView.ViewHolder(roleAnalysisItemBinding.root) {
        private val starName = roleAnalysisItemBinding.tvCharacterName
        private val starNum = roleAnalysisItemBinding.tvCharacterNum
        fun bindingData(data:String){
            starName.text = data.role
            starNum.text = data.num
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val roleAnalysisItemBinding =
            RoleAnalysisItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(roleAnalysisItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingData(list[position])
    }

    override fun getItemCount() = list.size
}