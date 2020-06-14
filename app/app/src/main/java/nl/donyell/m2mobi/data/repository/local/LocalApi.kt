package nl.donyell.m2mobi.data.repository.local

import io.reactivex.Completable
import io.reactivex.Flowable
import nl.donyell.m2mobi.domain.models.Photo

interface LocalApi {
    fun savePhotos(photos: List<Photo>): Completable

    fun getPhotos(): Flowable<List<Photo>>
}