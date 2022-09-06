package com.example.wanted_preonboarding_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivArticleImage: ImageView
        val tvTitle: TextView
        val tvAuthor: TextView
        val tvPublishedDate: TextView

        init {
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvPublishedDate = itemView.findViewById(R.id.tvPublishedDate)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.ivArticleImage)
            holder.tvTitle.text = article.title
            holder.tvAuthor.text = article.author
            holder.tvPublishedDate.text =  article.publishedAt
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listen: (Article) -> Unit) {
        onItemClickListener = listen
    }
}