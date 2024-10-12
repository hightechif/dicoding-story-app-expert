package com.fadhil.storyappexpert.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadhil.storyappexpert.domain.model.Session
import com.fadhil.storyappexpert.domain.usecase.ConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val configurationUseCase: ConfigurationUseCase
) : ViewModel() {

    fun getConfiguration() = configurationUseCase.get().asLiveData()

}