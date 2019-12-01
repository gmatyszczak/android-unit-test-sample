package pl.gmat.news.common.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.gmat.news.common.model.NEWS_TABLE_NAME
import pl.gmat.news.common.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM $NEWS_TABLE_NAME")
    fun loadAll(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<News>)

    @Query("SELECT * FROM $NEWS_TABLE_NAME WHERE id = :id")
    suspend fun loadNews(id: Int): News
}