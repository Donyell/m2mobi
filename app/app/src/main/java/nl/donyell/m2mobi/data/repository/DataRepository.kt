package nl.donyell.m2mobi.data.repository

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo

interface DataRepository {
    fun getPhotos(): Single<List<Photo>>

    fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>>
}