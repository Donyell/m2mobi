package nl.donyell.m2mobi.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo

interface DataRepository {
    fun refreshPhotos(): Completable

    fun getPhotos(): Flowable<List<Photo>>

    fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>>
}