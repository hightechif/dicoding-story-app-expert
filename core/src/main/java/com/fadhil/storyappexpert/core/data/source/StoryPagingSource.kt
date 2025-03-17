package com.fadhil.storyappexpert.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fadhil.storyappexpert.core.data.source.remote.StoryRemoteDataSource
import com.fadhil.storyappexpert.core.data.source.remote.response.ResStory
import com.fadhil.storyappexpert.core.domain.mapper.StoryMapper
import com.fadhil.storyappexpert.core.domain.model.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.mapstruct.factory.Mappers
import javax.inject.Inject

class StoryPagingSource @Inject constructor(
    private val remote: StoryRemoteDataSource
) : PagingSource<Int, Story>() {

    private val mapper = Mappers.getMapper(StoryMapper::class.java)
    var location = 1

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            var resStory: List<ResStory>? = null
            runBlocking(Dispatchers.IO) {
                remote.getAllStories(position, params.loadSize, location).collect { value ->
                    value.data?.listStory?.let {
                        resStory = it
                    }
                }
            }
            val list = resStory?.let { mapper.mapStoryResponseToDomainList(it) }
            LoadResult.Page(
                data = list ?: emptyList(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (list.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

}