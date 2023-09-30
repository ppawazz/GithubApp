package com.example.githubapp.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.databinding.ItemUserBinding
import com.example.githubapp.ui.detail.DetailUserActivity

class FavoriteAdapter(private val listUser: ArrayList<FavoriteUser>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteUserViewHolder>() {
    inner class FavoriteUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteUser) {
            binding.tvUser.text = user.username
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.ivUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size


    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteUserViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.USERNAME, user.username)
            holder.itemView.context.startActivity(intent)
        }
    }

}
