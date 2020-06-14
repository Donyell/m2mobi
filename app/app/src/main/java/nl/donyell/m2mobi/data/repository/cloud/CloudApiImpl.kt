package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.data.repository.cloud.mapper.GetCommentResponseMapper
import nl.donyell.m2mobi.data.repository.cloud.mapper.GetPhotoResponseMapper
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.domain.models.Comment
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class CloudApiImpl @Inject constructor(private val jsonPlaceholderApi: JsonPlaceholderApi) :
    CloudApi {

    override fun getPhotos(): Single<List<Photo>> {
        return jsonPlaceholderApi.getPhotos()
            .flattenAsObservable { it }
            .map { photosResponse ->
                GetPhotoResponseMapper.toPhoto(photosResponse)
            }.toList()
    }

    override fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<Comment>> {
        return jsonPlaceholderApi.getComments(getCommentsRequest.photoId)
            .flattenAsObservable { it }
            .map { commentResponse ->
                GetCommentResponseMapper.toComment(commentResponse)
            }.toList()
    }
}