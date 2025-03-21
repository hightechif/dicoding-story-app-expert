package com.fadhil.storyappexpert.core.domain.mapper

import com.fadhil.storyappexpert.core.data.source.local.entity.StoryEntity
import com.fadhil.storyappexpert.core.data.source.remote.response.ResStory
import com.fadhil.storyappexpert.core.domain.model.Story
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface StoryMapper {

    @Mappings(
        value = [
            Mapping(target = "createdTime", expression = "java(input.getCreatedTime())"),
            Mapping(target = "favorite", ignore = true)
        ]
    )
    fun mapStoryResponseToEntity(input: ResStory): StoryEntity

    @Mappings
    fun mapStoryResponseToEntityList(input: List<ResStory>): List<StoryEntity>

    @Mappings(
        value = [Mapping(
            target = "createdDate",
            expression = "java(input.getCreatedLocalDateTime())"
        )]
    )
    fun mapStoryEntityToDomain(input: StoryEntity): Story

    @Mappings
    fun mapStoryEntityToDomainList(input: List<StoryEntity>): List<Story>

    @Mappings(
        value = [Mapping(
            target = "createdTime",
            expression = "java(input.getCreatedTime())"
        )]
    )
    fun mapStoryDomainToEntity(input: Story): StoryEntity

    @Mappings(
        value = [
            Mapping(target = "createdDate", expression = "java(input.getCreatedDate())"),
            Mapping(target = "favorite", ignore = true)
        ]
    )
    fun mapStoryResponseToDomain(input: ResStory): Story

    @Mappings
    fun mapStoryResponseToDomainList(input: List<ResStory>): List<Story>

}