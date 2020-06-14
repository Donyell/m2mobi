package nl.donyell.m2mobi.data.mapper

import nl.donyell.m2mobi.data.models.response.GetCommentsResponse
import nl.donyell.m2mobi.domain.models.Comment

class GetCommentResponseMapper {

    fun toComment(commentsResponse: GetCommentsResponse): Comment{
        return Comment(
            commentsResponse.id,
            commentsResponse.name,
            commentsResponse.body
        )
    }
}