package pl.gmat.news.feature.main.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("SELECT * FROM $NEWS_TABLE_NAME")
    fun loadAll(): Flowable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>): Completable
}