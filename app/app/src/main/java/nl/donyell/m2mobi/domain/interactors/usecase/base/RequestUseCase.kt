package nl.donyell.m2mobi.domain.interactors.usecase.base

import io.reactivex.Single
import nl.donyell.m2mobi.domain.interactors.request.Request

interface RequestUseCase<R, E: Request> {
    fun execute(request: E): Single<R>
}