package com.fadhil.storyappexpert.ui.screen.home.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.databinding.ItemRowStoryBinding

class StoryViewHolder(private val binding: ItemRowStoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(list: List<Story>, position: Int, delegate: StoryDelegate?) {
        val user = list[position]
        binding.tvTitle.text = user.name
        binding.tvDate.text = user.getCreatedDateDisplay()
        binding.tvDescription.text = user.description
        binding.root.setOnClickListener {
            delegate?.setOnClickListener(binding.root, user.id)
        }
        Glide.with(binding.root.context).load(user.photoUrl).into(binding.ivStoryIllustration)
    }

}