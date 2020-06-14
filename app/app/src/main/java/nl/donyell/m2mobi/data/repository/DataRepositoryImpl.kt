package nl.donyell.m2mobi.data.repository

import io.reactivex.Single
import nl.donyell.m2mobi.data.mapper.GetCommentResponseMapper
import nl.donyell.m2mobi.data.mapper.GetPhotoResponseMapper
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.data.repository.cloud.CloudApi
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val cloudApi: CloudApi,
    private val photoResponseMapper: GetPhotoResponseMapper,
    private val commentResponseMapper: GetCommentResponseMapper
) : DataRepository {

    override fun getPhotos(): Single<List<Photo>> {
        return cloudApi.getPhotos()
            .flatMap { getPhotoResponses ->
            return@flatMap Single.just(
                getPhotoResponses.map { photoResponse ->
                    photoResponseMapper.toPhoto(photoResponse)
                })
        }
    }

    override fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>> {
        return cloudApi.getComments(getCommentsRequest)
            .flattenAsObservable {it}
            .map { commentResponse ->
                 commentResponseMapper.toComment(commentResponse)
            }.toList()
    }
}