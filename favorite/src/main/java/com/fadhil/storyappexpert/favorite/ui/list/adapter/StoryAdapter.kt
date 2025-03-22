package com.fadhil.storyappexpert.favorite.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.storyappexpert.core.domain.model.Favorite
import com.fadhil.storyappexpert.databinding.ItemRowStoryBinding
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryDelegate

class StoryAdapter : RecyclerView.Adapter<StoryViewHolder>() {

    var delegate: PagingStoryDelegate? = null
    private lateinit var binding: ItemRowStoryBinding

    private val diffUtil = object : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite):
                Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite):
                Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemRowStoryBinding.inflate(layoutInflater, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList, position, delegate)
    }

    override fun getItemCount() = asyncListDiffer.currentList.size

    fun setData(newList: List<Favorite>) {
        asyncListDiffer.submitList(newList)
    }

    fun clearData() {
        asyncListDiffer.submitList(emptyList())
    }

}