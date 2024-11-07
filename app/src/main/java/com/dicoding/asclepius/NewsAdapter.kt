package com.dicoding.asclepius

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.NewsItemBinding

class NewsAdapter : ListAdapter<ArticlesItem, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articlesItem: ArticlesItem){
            Glide.with(itemView.context)
                .load(articlesItem.urlToImage)
                .into(binding.newsImage)

            binding.tvNewsTitle.text = articlesItem.title
            binding.tvNewsDescription.text = articlesItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem == newItem
    }
}