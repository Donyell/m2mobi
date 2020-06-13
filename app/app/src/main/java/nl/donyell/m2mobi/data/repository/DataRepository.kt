package nl.donyell.m2mobi.data.repository

import io.reactivex.Single
import nl.donyell.m2mobi.domain.models.Photo

interface DataRepository {
    fun getPhotos(): Single<List<Photo>>
}