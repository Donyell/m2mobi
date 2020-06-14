package nl.donyell.m2mobi.domain.interactors

import io.reactivex.Flowable

interface FlowableUseCase<R> {
    fun execute(): Flowable<R>
}