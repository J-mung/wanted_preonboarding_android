package com.example.wanted_preonboarding_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.models.Category

class CategoryAdapter(
    private val context: Context,
    private val dataset: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivCategory: ImageView = itemView.findViewById(R.id.gridImg)
        val tvCategory: TextView = itemView.findViewById(R.id.gridText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = dataset[position]
        holder.itemView.apply {
            holder.tvCategory.text = context.resources.getString(item.category)
            holder.ivCategory.setImageResource(item.image)
            setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }

    }

    override fun getItemCount() = dataset.size

    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listen: (Category) -> Unit) {
        onItemClickListener = listen
    }
}