package com.fadhil.storyappexpert.favorite.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.storyappexpert.core.domain.model.Favorites
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.favorite.databinding.FragmentFavoriteStoryListBinding
import com.fadhil.storyappexpert.favorite.ui.list.adapter.StoryAdapter
import com.fadhil.storyappexpert.ui.screen.home.list.StoryListFragmentDirections
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryDelegate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class FavoriteStoryListFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteStoryListBinding

    private val viewModel: FavoriteStoryListViewModel by viewModels()
    private val adapter = StoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteStoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
        setupObserver()
        initData()
    }

    private fun setupView() {
        with(binding) {
            rvUser.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@FavoriteStoryListFragment.adapter
            }
        }
    }

    private fun setupListener() {
        val callback = object : PagingStoryDelegate {
            override fun setOnClickListener(view: View, id: String) {
                val toDetailUserFragment =
                    StoryListFragmentDirections.actionStoryListFragmentToStoryDetailFragment(
                        id
                    )
                view.findNavController().navigate(toDetailUserFragment)
            }

            override fun setOnFavoriteListener(story: Story, position: Int) {
                lifecycleScope.launch {
                    // update favorite status
                }
            }
        }
        adapter.delegate = callback
        adapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    binding.rvUser.layoutManager?.scrollToPosition(0)
                }
            }
        })
    }

    private fun setupObserver() {

    }

    private fun initData() {
        if (requireActivity().intent.extras?.isEmpty != true) {
            val args = FavoriteStoryListFragmentArgs.fromBundle(requireActivity().intent.extras!!)
            val json = args.jsonData

            val favorites =
                Gson().fromJson<Favorites>(json, object : TypeToken<Favorites>() {}.type)
            adapter.setData(favorites.list)
        }
    }


}