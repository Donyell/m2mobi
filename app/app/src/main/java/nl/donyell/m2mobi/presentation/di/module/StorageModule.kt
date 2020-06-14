package nl.donyell.m2mobi.presentation.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import nl.donyell.m2mobi.data.repository.local.AppDatabase
import nl.donyell.m2mobi.data.repository.local.PhotoDao
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "m2mobi"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideShowDao(database: AppDatabase): PhotoDao {
        return database.photoDao()
    }
}