package nl.donyell.m2mobi.data.repository.cloud.mapper

import nl.donyell.m2mobi.data.repository.cloud.models.GetPhotosResponse
import nl.donyell.m2mobi.domain.models.Photo

object GetPhotoResponseMapper {

    fun toPhoto(photosResponse: GetPhotosResponse): Photo {
        return Photo(
            photosResponse.id,
            photosResponse.title,
            photosResponse.url,
            photosResponse.thumbnailUrl
        )
    }
}