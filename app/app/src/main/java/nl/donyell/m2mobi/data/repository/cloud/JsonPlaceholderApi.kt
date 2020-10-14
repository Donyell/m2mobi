package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.data.repository.cloud.models.GetCommentsResponse
import nl.donyell.m2mobi.data.repository.cloud.models.GetPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface JsonPlaceholderApi {

    @GET("photos")
    fun getPhotos(): Single<List<GetPhotosResponse>>

    @GET("photos/{photoId}/comments")
    fun getComments(@Path("photoId") photoId: Long): Single<List<GetCommentsResponse>>
}
