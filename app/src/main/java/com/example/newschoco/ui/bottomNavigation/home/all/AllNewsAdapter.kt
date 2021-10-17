package com.example.newschoco.ui.bottomNavigation.home.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newschoco.R
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.databinding.ItemRecyclerEverytingBinding
import com.squareup.picasso.Picasso

class AllNewsAdapter(private val listener : (item : EverythingArticles?)-> Unit): PagingDataAdapter<EverythingArticles,AllNewsViewHolder>(diff_util_everything) {

    companion object{
        val diff_util_everything= object : DiffUtil.ItemCallback<EverythingArticles>(){
            override fun areItemsTheSame(oldItem: EverythingArticles, newItem: EverythingArticles): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: EverythingArticles, newItem: EverythingArticles): Boolean {
                return oldItem == newItem
                        && oldItem.title == newItem.title
                        && oldItem.publishedAt == newItem.publishedAt
                        && oldItem.author == newItem.author
                        && oldItem.content == newItem.content
                        && oldItem.source == newItem.source
                        && oldItem.url == newItem.url
                        && oldItem.urlToImage == newItem.urlToImage
                        && oldItem.description == newItem.description
            }

        }
    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        return AllNewsViewHolder.create(parent,listener)
    }
}

class AllNewsViewHolder(
    private val binding: ItemRecyclerEverytingBinding,
   private val listener: (item: EverythingArticles?) -> Unit
): RecyclerView.ViewHolder(binding.root){
    fun bind(item: EverythingArticles?) {
        binding.tvTitle.text = item?.title
        binding.tvSource.text = item?.source?.name

        val image = item?.urlToImage
        Picasso.get().load(image).into(binding.ivImage)

        binding.parentView.setOnClickListener {
            item.let { it1 -> listener.invoke(it1) }
        }

    }

    companion object{
        fun create(parent: ViewGroup, listener: (item: EverythingArticles?) -> Unit) : AllNewsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_everyting,
                parent,
                false)
            val binding = ItemRecyclerEverytingBinding.bind(view)
            return AllNewsViewHolder(binding,listener)
        }
    }
}