package nl.donyell.m2mobi.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.donyell.m2mobi.data.repository.local.models.LocalPhoto

@Database(entities = [LocalPhoto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}