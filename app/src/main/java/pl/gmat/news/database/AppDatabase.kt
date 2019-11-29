package pl.gmat.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.gmat.news.feature.news.News
import pl.gmat.news.feature.news.dao.NewsDao

@Database(
    entities = [
        News::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}