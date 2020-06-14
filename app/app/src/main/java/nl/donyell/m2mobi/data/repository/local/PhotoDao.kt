package nl.donyell.m2mobi.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import nl.donyell.m2mobi.data.repository.local.models.LocalPhoto

@Dao
interface PhotoDao {
    @Query("SELECT * FROM  localphoto")
    fun getPhotos(): Flowable<List<LocalPhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(photos: List<LocalPhoto>): Completable
}