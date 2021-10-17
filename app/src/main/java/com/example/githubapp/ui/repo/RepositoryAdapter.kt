package com.example.githubapp.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.model.Repository
import com.example.githubapp.databinding.ItemRepositoryBinding

class RepositoryAdapter:
    ListAdapter<Repository, RepositoryAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }
    }


    inner class ViewHolder(private var binding: ItemRepositoryBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(repository: Repository){
            binding.tvRepoName.text = repository.name
            binding.tvLanguage.text = repository.language
            binding.tvVisibility.text = if (repository.isPrivate) "Private" else "Public"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bind(repository)
    }

}