package com.fadhil.storyappexpert.favorite.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteStoryListViewModel : ViewModel() {

    val size = MutableLiveData<Int>()
    val location = MutableLiveData<Int>()

    fun setSize(input: Int) {
        size.value = input
    }

    fun setLocation(input: Int) {
        location.value = input
    }
    
}