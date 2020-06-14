package nl.donyell.m2mobi.presentation.di.module

import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.data.mapper.GetCommentResponseMapper
import nl.donyell.m2mobi.data.mapper.GetPhotoResponseMapper

@Module
class MapperModule {

    @Provides
    fun provideGetPhotoResponseMapper(): GetPhotoResponseMapper {
        return GetPhotoResponseMapper()
    }

    @Provides
    fun provideGetCommentsResponseMapper(): GetCommentResponseMapper {
        return GetCommentResponseMapper()
    }
}