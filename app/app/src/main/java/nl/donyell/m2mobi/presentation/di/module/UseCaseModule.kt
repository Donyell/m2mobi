package nl.donyell.m2mobi.presentation.di.module

import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.domain.interactors.usecase.GetCommentsUseCase
import nl.donyell.m2mobi.domain.interactors.usecase.GetPhotosUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideGetPhotosUseCase(dataRepository: DataRepository): GetPhotosUseCase {
        return GetPhotosUseCase(dataRepository)
    }

    @Provides
    fun provideGetCommentsUseCase(dataRepository: DataRepository): GetCommentsUseCase {
        return GetCommentsUseCase(dataRepository)
    }
}