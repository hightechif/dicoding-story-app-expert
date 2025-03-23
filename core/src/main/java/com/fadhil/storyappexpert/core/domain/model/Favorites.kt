package com.fadhil.storyappexpert.core.domain.model

data class Favorites(
    val list: List<Favorite>
) {

    companion object {
        fun build(input: List<Story>) = Favorites(
            list = input.map { story ->
                Favorite(
                    id = "fav-${story.id}",
                    storyId = story.id,
                    name = story.name,
                    description = story.description,
                    photoUrl = story.photoUrl,
                    displayedDate = story.getCreatedDateDisplay(),
                    lat = story.lat,
                    lon = story.lon,
                    favorite = story.favorite == true,
                )
            }
        )
    }

}
