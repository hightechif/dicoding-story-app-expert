package com.fadhil.storyappexpert.ui.screen.home.list.adapter

import android.view.View
import com.fadhil.storyappexpert.core.domain.model.Story

interface PagingStoryDelegate {
    fun setOnClickListener(view: View, id: String)
    fun setOnFavoriteListener(story: Story, position: Int)
}