package nl.donyell.m2mobi.presentation.di.module

import dagger.Binds
import dagger.Module
import nl.donyell.m2mobi.data.repository.DataRepository
import nl.donyell.m2mobi.data.repository.DataRepositoryImpl
import nl.donyell.m2mobi.data.repository.cloud.CloudApi
import nl.donyell.m2mobi.data.repository.cloud.CloudApiImpl
import nl.donyell.m2mobi.data.repository.local.LocalApi
import nl.donyell.m2mobi.data.repository.local.LocalApiImpl

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCloudApi(cloudApi: CloudApiImpl): CloudApi

    @Binds
    abstract fun provideLocalApi(localApi: LocalApiImpl): LocalApi

    @Binds
    abstract fun provideDataRepository(dataRepository: DataRepositoryImpl): DataRepository
}