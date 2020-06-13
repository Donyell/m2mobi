package nl.donyell.m2mobi.presentation.di.module

import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.domain.interactors.GetPhotosUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideGetPhotosUseCase(dataRepository: DataRepository): GetPhotosUseCase {
        return GetPhotosUseCase(dataRepository)
    }
}