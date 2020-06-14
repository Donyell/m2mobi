package nl.donyell.m2mobi.data.mapper

import nl.donyell.m2mobi.data.models.response.GetPhotosResponse
import nl.donyell.m2mobi.domain.models.Photo

class GetPhotoResponseMapper {

    fun toPhoto(photosResponse: GetPhotosResponse): Photo {
        return Photo(
            photosResponse.id,
            photosResponse.title,
            photosResponse.url,
            photosResponse.thumbnailUrl
        )
    }
}