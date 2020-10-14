package nl.donyell.m2mobi.domain.interactors.usecase

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.GetCommentsRequest
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.domain.models.Comment
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

    operator fun invoke(request: GetCommentsRequest): Single<List<Comment>> {
        return dataRepository.getComments(request)
    }
}
