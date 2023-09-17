package com.example.githubapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.databinding.ItemUserBinding

class UserAdapter(private var userList: List<ItemsItem>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.tvUser.text = user.login
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.ivUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        user?.let { holder.bind(it) }
    }

    fun setData(newList: List<ItemsItem>) {
        userList = newList
        notifyDataSetChanged()
    }
}