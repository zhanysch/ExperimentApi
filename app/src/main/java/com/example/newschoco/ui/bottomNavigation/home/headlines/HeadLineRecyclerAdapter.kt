package com.example.newschoco.ui.bottomNavigation.home.headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newschoco.R
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.databinding.ItemRecyclerHeadlineBinding
import com.squareup.picasso.Picasso

class HeadLineRecyclerAdapter: PagingDataAdapter<Articles,HeadViewHolder>(diff_util_headline){

    companion object{
        val diff_util_headline= object :DiffUtil.ItemCallback<Articles>(){
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.id == newItem.id
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

    override fun onBindViewHolder(holder: HeadViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadViewHolder {
        return HeadViewHolder.create(parent)
    }


}

class HeadViewHolder(private val binding:  ItemRecyclerHeadlineBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Articles?) {
        binding.tvTitle.text = item?.title
        binding.tvSource.text = item?.source?.name

        val image = item?.urlToImage
        Picasso.get().load(image).into(binding.ivImage)
    }

    companion object{
        fun create(parent: ViewGroup) : HeadViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_headline,
                parent,
                false)
            val binding = ItemRecyclerHeadlineBinding.bind(view)
            return HeadViewHolder(binding)
        }
    }
}