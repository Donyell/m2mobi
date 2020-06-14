package nl.donyell.m2mobi.domain.interactors

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute(): Completable
}