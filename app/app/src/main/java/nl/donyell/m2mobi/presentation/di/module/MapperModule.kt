package nl.donyell.m2mobi.presentation.di.module

import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.data.mapper.GetPhotoResponseMapper

@Module
class MapperModule {

    @Provides
    fun providerGetPhotoResponseMapper(): GetPhotoResponseMapper {
        return GetPhotoResponseMapper()
    }
}