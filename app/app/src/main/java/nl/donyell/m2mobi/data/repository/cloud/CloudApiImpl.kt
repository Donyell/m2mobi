package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.data.models.GetPhotosResponse
import javax.inject.Inject

class CloudApiImpl @Inject constructor(private val jsonPlaceholderApi: JsonPlaceholderApi) : CloudApi {

    override fun getPhotos(): Single<List<GetPhotosResponse>> = jsonPlaceholderApi.getPhotos()
}