package com.example.newschoco.ui.bottomNavigation.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newschoco.R
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.databinding.ItemRecyclerHeadlineFeatureBinding
import com.squareup.picasso.Picasso

class HeadLineFeatureAdapterAdapter(private val vm : FeatureViewModel): ListAdapter<Articles,HeadFeatureViewHolder>(diff_util_headline){

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

    override fun onBindViewHolder(holder: HeadFeatureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadFeatureViewHolder {
        return HeadFeatureViewHolder.create(parent,vm)
    }


}

class HeadFeatureViewHolder(
    private val binding: ItemRecyclerHeadlineFeatureBinding,
   private val vm: FeatureViewModel

) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Articles?) {
        binding.tvTitle.text = item?.title
        binding.tvDesc.text = item?.description
        binding.tvContent.text = item?.content
        binding.tvAuthor.text = item?.author
        binding.tvSource.text = item?.source?.name
        binding.tvURL.text = item?.url
        binding.tvPublishedAt.text = item?.publishedAt
        val image = item?.urlToImage
        Picasso.get().load(image).into(binding.ivURLTolmage)

        binding.checkNews.setOnCheckedChangeListener { compoundButton, b ->
            item?.isChecked = b
            item?.let { vm.update(it) }
        }
    }

    companion object{
        fun create(parent: ViewGroup, vm: FeatureViewModel) : HeadFeatureViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_headline_feature,
                parent,
                false)
            val binding = ItemRecyclerHeadlineFeatureBinding.bind(view)
            return HeadFeatureViewHolder(binding,vm)
        }
    }
}