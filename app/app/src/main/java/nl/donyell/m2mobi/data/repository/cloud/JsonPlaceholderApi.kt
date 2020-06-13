package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.data.models.GetPhotosResponse
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("photos")
    fun getPhotos(): Single<List<GetPhotosResponse>>
}