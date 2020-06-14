package nl.donyell.m2mobi.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import nl.donyell.m2mobi.data.repository.cloud.CloudApi
import nl.donyell.m2mobi.data.repository.local.LocalApi
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val cloudApi: CloudApi,
    private val localApi: LocalApi
) : DataRepository {

    override fun refreshPhotos(): Completable {
        return cloudApi.getPhotos()
            .flatMapCompletable { localApi.savePhotos(it) }
    }

    override fun getPhotos(): Flowable<List<Photo>> {
        return localApi.getPhotos()
    }

    override fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>> {
        return cloudApi.getComments(getCommentsRequest)
    }
}