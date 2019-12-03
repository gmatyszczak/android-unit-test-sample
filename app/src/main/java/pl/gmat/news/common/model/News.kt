package pl.gmat.news.common.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val NEWS_TABLE_NAME = "news"

@Entity(tableName = NEWS_TABLE_NAME)
@Parcelize
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
) : Parcelable