package nl.donyell.m2mobi.data.models

data class GetPhotosResponse(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)