package nl.donyell.m2mobi.domain.interactors.usecase

import io.reactivex.Completable
import nl.donyell.m2mobi.data.repository.DataRepository
import javax.inject.Inject

class RefreshPhotosUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

    operator fun invoke(): Completable {
        return dataRepository.refreshPhotos()
    }
}
