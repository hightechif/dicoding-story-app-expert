package com.fadhil.storyappexpert.maps.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.fadhil.storyappexpert.domain.usecase.IStoryUseCase

@dagger.hilt.android.lifecycle.HiltViewModel
class StoryMapsViewModel @javax.inject.Inject constructor(
    private val storyUseCase: IStoryUseCase
) : androidx.lifecycle.ViewModel() {

    val page = MutableLiveData<Int>()
    val size = MutableLiveData<Int>()
    val location = MutableLiveData<Int>()

    fun setPage(input: Int) {
        page.value = input
    }

    fun setSize(input: Int) {
        size.value = input
    }

    fun setLocation(input: Int) {
        location.value = input
    }

    fun getAllStories(reload: Boolean) =
        storyUseCase.getAllStory(page.value, size.value, location.value, reload).asLiveData()

}