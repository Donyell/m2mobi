package nl.donyell.m2mobi.data.models.response

data class GetCommentsResponse(
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String)