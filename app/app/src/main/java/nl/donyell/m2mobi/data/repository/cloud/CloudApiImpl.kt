package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.data.models.response.GetCommentsResponse
import nl.donyell.m2mobi.data.models.response.GetPhotosResponse
import javax.inject.Inject

class CloudApiImpl @Inject constructor(private val jsonPlaceholderApi: JsonPlaceholderApi) : CloudApi {

    override fun getPhotos(): Single<List<GetPhotosResponse>> = jsonPlaceholderApi.getPhotos()

    override fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<GetCommentsResponse>> {
        return jsonPlaceholderApi.getComments(getCommentsRequest.photoId)
    }
}