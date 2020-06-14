package nl.donyell.m2mobi.domain.interactors.usecase.base

import io.reactivex.Flowable

interface FlowableUseCase<R> {
    fun execute(): Flowable<R>
}