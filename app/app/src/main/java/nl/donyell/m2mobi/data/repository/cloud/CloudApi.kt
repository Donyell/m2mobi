package nl.donyell.m2mobi.data.repository.cloud

import io.reactivex.Single
import nl.donyell.m2mobi.data.models.GetPhotosResponse

interface CloudApi {
    fun getPhotos(): Single<List<GetPhotosResponse>>
}