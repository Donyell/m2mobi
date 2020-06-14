package nl.donyell.m2mobi.data.repository.cloud.mapper

import nl.donyell.m2mobi.data.repository.cloud.models.GetCommentsResponse
import nl.donyell.m2mobi.domain.models.Comment

object GetCommentResponseMapper {

    fun toComment(commentsResponse: GetCommentsResponse): Comment {
        return Comment(
            commentsResponse.id,
            commentsResponse.name,
            commentsResponse.body
        )
    }
}