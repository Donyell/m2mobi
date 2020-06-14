package nl.donyell.m2mobi.data.repository.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalPhoto constructor(
    @PrimaryKey
    val id: Long,
    val title: String,
    val imageUrl: String,
    val thumbnailUrl: String
)