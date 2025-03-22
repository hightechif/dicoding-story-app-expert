package com.fadhil.storyappexpert.ui.screen.home.list

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.storyappexpert.core.domain.model.Favorite
import com.fadhil.storyappexpert.core.domain.model.Story
import com.fadhil.storyappexpert.core.navigation.ModuleNavigator
import com.fadhil.storyappexpert.core.util.DynamicModuleDownloadUtil
import com.fadhil.storyappexpert.databinding.FragmentStoryListBinding
import com.fadhil.storyappexpert.ui.screen.add.AddStoryActivity
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.LoadingStateAdapter
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryAdapter
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.PagingStoryDelegate
import com.fadhil.storyappexpert.ui.screen.home.list.adapter.StoryComparator
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class StoryListFragment : Fragment(), ModuleNavigator,
    DynamicModuleDownloadUtil.DynamicDownloadListener {

    private lateinit var binding: FragmentStoryListBinding
    private val viewModel: StoryListViewModel by viewModels()
    private val mStoryPagingAdapter = PagingStoryAdapter(StoryComparator)
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil

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
        binding = FragmentStoryListBinding.inflate(inflater, container, false)
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
                    viewModel.addToFavorites(story).collect {
                        if (it) mStoryPagingAdapter.notifyItemChanged(position)
                    }
                }
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
            navigateToStoryMapsActivity()
        }

        binding.fabFavorite.setOnClickListener {
            viewModel.isFabFavoriteClicked.postValue(true)
            initDynamicModule()
        }
    }

    private fun setupObserver() {
        viewModel.getFavoriteStories().observeForever { }
    }

    private fun initData() {
        viewModel.setSize(10)
        viewModel.setLocation(1)
        getPagingStory()
    }

    private fun reload() {
        mStoryPagingAdapter.refresh()
    }

    private fun getPagingStory() {
        // Activities can use lifecycleScope directly; fragments use
        // viewLifecycleOwner.lifecycleScope.
        viewModel.stories.observe(viewLifecycleOwner) { pagingData ->
            mStoryPagingAdapter.submitData(lifecycle, pagingData)
        }
    }

    private fun initDynamicModule() {
        initDynamicModuleDownloadUtil()
        openDynamicActivityIfDownloaded()
    }

    private fun initDynamicModuleDownloadUtil() {
        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(requireContext(), this)
    }

    private fun openDynamicActivityIfDownloaded() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded("favorite")) {
            openDynamicActivity()
        } else {
            dynamicModuleDownloadUtil.downloadDynamicModule("favorite")
        }
    }

    override fun onDynamicModuleDownloaded() {
        openDynamicActivity()
    }

    private fun openDynamicActivity() {
        viewModel.getFavoriteStories().observe(viewLifecycleOwner) { list ->
            if (viewModel.isFabFavoriteClicked.value == true) {
                Timber.d("DEBUG FADHIL ---- list on Fragment = $list")
                val json = Gson().toJson(Favorite(list))
                navigateToFavoriteStoryActivity(jsonData = json)
                viewModel.isFabFavoriteClicked.postValue(false)
            }
        }
    }

}