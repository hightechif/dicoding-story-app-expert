package com.fadhil.storyappexpert.favorite.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.storyappexpert.favorite.databinding.ActivityFavoritStoryBinding

class FavoriteStoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritStoryBinding
    private val viewModel: FavoriteStoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObserver()
        setupListener()
        initData()
    }

    private fun setupView() {

    }

    private fun setupObserver() {

    }

    private fun setupListener() {

    }

    private fun initData() {

    }
}