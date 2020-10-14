package nl.donyell.m2mobi.data.repository.local

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import nl.donyell.m2mobi.data.repository.local.mapper.PhotoMapper
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class LocalApiImpl @Inject constructor(private val photoDao: PhotoDao) : LocalApi {

    override fun savePhotos(photos: List<Photo>): Completable {
        val localPhotos = photos.map { PhotoMapper.toLocalPhoto(it) }
        return photoDao.insertAllPhotos(localPhotos)
    }

    override fun getPhotos(): Flowable<List<Photo>> {
        return photoDao.getPhotos()
            .flatMapSingle { photos ->
                Observable.fromIterable(photos)
                    .map { PhotoMapper.fromLocalPhoto(it) }
                    .toList()
            }
    }
}
