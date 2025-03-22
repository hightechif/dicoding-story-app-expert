package com.fadhil.storyappexpert.favorite.ui.list.adapter

import android.view.View
import com.fadhil.storyappexpert.core.domain.model.Story

interface StoryDelegate {
    fun setOnClickListener(view: View, id: String)
    fun setOnFavoriteListener(story: Story, position: Int)
}