package com.fadhil.storyappexpert.favorite.ui.list

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.favorite.databinding.FragmentFavoriteStoryListBinding
import com.fadhil.storyappexpert.ui.screen.add.AddStoryActivity
import com.fadhil.storyappexpert.ui.screen.home.list.StoryListFragmentDirections
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.LoadingStateAdapter
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryAdapter
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.StoryComparator
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.StoryDelegate
import com.google.android.material.snackbar.Snackbar

class FavoriteStoryListFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteStoryListBinding

    //private val viewModel: FavoriteStoryListViewModel by viewModels()
    private val mStoryPagingAdapter = PagingStoryAdapter(StoryComparator)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                Snackbar.make(
                    binding.root,
                    "Upload process complete.",
                    Snackbar.LENGTH_SHORT
                ).show()
                reload()
            }
        }

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
                adapter = mStoryPagingAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        mStoryPagingAdapter.retry()
                    }
                )
            }
        }
    }

    private fun setupListener() {
        val callback = object : StoryDelegate {
            override fun setOnClickListener(view: View, id: String) {
                val toDetailUserFragment =
                    StoryListFragmentDirections.actionStoryListFragmentToStoryDetailFragment(
                        id
                    )
                view.findNavController().navigate(toDetailUserFragment)
            }

            override fun setOnFavoriteListener(story: Story, position: Int) {
                // remove
            }
        }
        mStoryPagingAdapter.delegate = callback
        mStoryPagingAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    binding.rvUser.layoutManager?.scrollToPosition(0)
                }
            }
        })

        binding.fabAdd.setOnClickListener {
            AddStoryActivity.open(requireActivity(), resultLauncher)
        }

        binding.fabMap.setOnClickListener {
            //navigateToStoryMapsActivity()
        }

        binding.fabFavorite.setOnClickListener {
            //initDynamicModule()
        }
    }

    private fun setupObserver() {

    }

    private fun initData() {
        //viewModel.setSize(10)
        //viewModel.setLocation(1)
        getPagingStory()
    }

    private fun reload() {
        mStoryPagingAdapter.refresh()
    }

    private fun getPagingStory() {
        // Activities can use lifecycleScope directly; fragments use
        // viewLifecycleOwner.lifecycleScope.
//        viewModel.stories.observe(viewLifecycleOwner) { pagingData ->
//            mStoryPagingAdapter.submitData(lifecycle, pagingData)
//        }
    }


}