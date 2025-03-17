package com.fadhil.storyappexpert.ui.screen.home.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fadhil.storyappexpert.core.domain.model.Story

object StoryComparator : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }
}