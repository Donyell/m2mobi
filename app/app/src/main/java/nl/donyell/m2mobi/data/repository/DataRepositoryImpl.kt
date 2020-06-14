package nl.donyell.m2mobi.data.repository

import io.reactivex.Single
import nl.donyell.m2mobi.data.mapper.GetCommentResponseMapper
import nl.donyell.m2mobi.data.mapper.GetPhotoResponseMapper
import nl.donyell.m2mobi.data.repository.cloud.CloudApi
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val cloudApi: CloudApi) : DataRepository {

    override fun getPhotos(): Single<List<Photo>> {
        return cloudApi.getPhotos()
            .flattenAsObservable { it }
            .map { photosResponse ->
                GetPhotoResponseMapper.toPhoto(photosResponse)
            }.toList()
    }

    override fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>> {
        return cloudApi.getComments(getCommentsRequest)
            .flattenAsObservable { it }
            .map { commentResponse ->
                GetCommentResponseMapper.toComment(commentResponse)
            }.toList()
    }
}