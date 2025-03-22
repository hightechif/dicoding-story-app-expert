package com.fadhil.storyappexpert.favorite.ui.list.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadhil.storyappexpert.core.domain.model.Favorite
import com.fadhil.storyappexpert.databinding.ItemRowStoryBinding
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryDelegate

class StoryViewHolder(private val binding: ItemRowStoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(list: List<Favorite>, position: Int, delegate: PagingStoryDelegate?) {
        if (list.isNotEmpty()) {
            val story = list[position]
            binding.ivFavorite.isVisible = false
            binding.tvTitle.text = story.name
            binding.tvDate.text = story.displayedDate
            binding.tvDescription.text = story.description
            binding.root.setOnClickListener {
                delegate?.setOnClickListener(binding.root, story.id)
            }
            Glide.with(binding.root.context).load(story.photoUrl).into(binding.ivStoryIllustration)
        }
    }

}