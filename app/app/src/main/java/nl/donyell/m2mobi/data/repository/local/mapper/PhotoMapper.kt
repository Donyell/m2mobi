package nl.donyell.m2mobi.data.repository.local.mapper

import nl.donyell.m2mobi.data.repository.local.models.LocalPhoto
import nl.donyell.m2mobi.domain.models.Photo

object PhotoMapper {

    fun toLocalPhoto(photo: Photo): LocalPhoto {
        return LocalPhoto(
            photo.id,
            photo.title,
            photo.imageUrl,
            photo.thumbnailUrl
        )
    }

    fun fromLocalPhoto(localPhoto: LocalPhoto): Photo {
        return Photo(
            localPhoto.id,
            localPhoto.title,
            localPhoto.imageUrl,
            localPhoto.thumbnailUrl
        )
    }
}