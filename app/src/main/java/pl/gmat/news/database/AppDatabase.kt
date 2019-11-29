package pl.gmat.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.gmat.news.feature.news.dao.NewsDao
import pl.gmat.news.feature.news.dao.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}