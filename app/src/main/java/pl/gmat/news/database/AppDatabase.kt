package pl.gmat.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.gmat.news.common.dao.NewsDao
import pl.gmat.news.common.model.News

@Database(
    entities = [
        News::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}