package pl.gmat.news.di

import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import pl.gmat.news.NewsApp
import pl.gmat.news.database.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()

    @Provides
    @Singleton
    fun provideDatabase(application: NewsApp): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "appDatabase.db").build()
}