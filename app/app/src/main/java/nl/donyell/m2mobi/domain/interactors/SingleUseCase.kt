package nl.donyell.m2mobi.domain.interactors

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute(): Single<R>
}