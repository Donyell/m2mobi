package nl.donyell.m2mobi.domain.interactors

import io.reactivex.Single
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val dataRepository: DataRepository): SingleUseCase<List<Photo>>{

    override fun execute(): Single<List<Photo>> {
        return dataRepository.getPhotos()
    }
}