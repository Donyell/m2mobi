package nl.donyell.m2mobi.domain.interactors.usecase

import io.reactivex.Flowable
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.domain.interactors.usecase.base.FlowableUseCase
import nl.donyell.m2mobi.domain.models.Photo
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val dataRepository: DataRepository) :
    FlowableUseCase<List<Photo>> {

    override fun execute(): Flowable<List<Photo>> {
        return dataRepository.getPhotos()
    }
}