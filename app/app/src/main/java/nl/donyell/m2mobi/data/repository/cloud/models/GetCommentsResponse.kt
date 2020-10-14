package nl.donyell.m2mobi.data.repository.cloud.models

data class GetCommentsResponse(
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
)
