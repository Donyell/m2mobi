package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo

interface CloudApi {
    fun getPhotos(): Single<List<Photo>>

    fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>>
}