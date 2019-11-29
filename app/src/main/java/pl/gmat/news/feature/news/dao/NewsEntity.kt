package pl.gmat.news.feature.news.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEWS_TABLE_NAME = "news"

@Entity(tableName = NEWS_TABLE_NAME)
data class NewsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)