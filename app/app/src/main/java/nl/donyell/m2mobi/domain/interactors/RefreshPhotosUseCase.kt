package nl.donyell.m2mobi.domain.interactors

import io.reactivex.Completable
import nl.donyell.m2mobi.data.repository.DataRepository
import javax.inject.Inject

class RefreshPhotosUseCase @Inject constructor(private val dataRepository: DataRepository) :
    CompletableUseCase {

    override fun execute(): Completable {
        return dataRepository.refreshPhotos()
    }
}