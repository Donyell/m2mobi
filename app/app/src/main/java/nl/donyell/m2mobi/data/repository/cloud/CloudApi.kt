package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.data.models.response.GetCommentsResponse
import nl.donyell.m2mobi.data.models.response.GetPhotosResponse

interface CloudApi {
    fun getPhotos(): Single<List<GetPhotosResponse>>

    fun getComments(getCommentsRequest: GetCommentsRequest): Single<List<GetCommentsResponse>>
}