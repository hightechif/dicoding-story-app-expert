package com.fadhil.storyappexpert.core.domain.model

data class Favorites(
    val list: List<Favorite>
) {

    companion object {
        fun build(input: List<Story>) = Favorites(
            list = input.map {
                Favorite(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    photoUrl = it.photoUrl,
                    displayedDate = it.getCreatedDateDisplay(),
                    lat = it.lat,
                    lon = it.lon,
                    favorite = it.favorite,
                )
            }
        )
    }

}
