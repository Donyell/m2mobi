package nl.donyell.m2mobi.domain.interactors.usecase.base

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute(): Completable
}