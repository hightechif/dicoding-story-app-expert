package com.fadhil.storyappexpert.ui.screen.home.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.databinding.ItemRowStoryBinding

class PagingStoryViewHolder(private val binding: ItemRowStoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(story: Story?, position: Int, delegate: StoryDelegate?) {
        story?.let {
            binding.tvTitle.text = story.name
            binding.tvDate.text = story.getCreatedDateDisplay()
            binding.tvDescription.text = story.description
            binding.ivFavorite.isSelected = story.favorite
            binding.ivFavorite.setOnClickListener {
                delegate?.setOnFavoriteListener(story, position)
            }
            binding.clCard.setOnClickListener {
                delegate?.setOnClickListener(binding.root, story.id)
            }
            Glide.with(binding.root.context).load(story.photoUrl).into(binding.ivStoryIllustration)
        }
    }

}