package pl.gmat.news.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEWS_TABLE_NAME = "news"

@Entity(tableName = NEWS_TABLE_NAME)
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)