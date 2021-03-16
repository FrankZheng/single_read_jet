package com.xzheng.singlereadjet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.xzheng.singlereadjet.databinding.ArticleItemViewBinding
import com.xzheng.singlereadjet.network.Article

class ArticleListAdapter : ListAdapter<Article, ArticleListAdapter.ArticleItemViewHolder>(DiffCallback) {

    class ArticleItemViewHolder(private val binding: ArticleItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article:Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder(ArticleItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment())
        }
    }
}