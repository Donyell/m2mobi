package nl.donyell.m2mobi.domain.models

data class Photo(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val thumbnailUrl: String
)