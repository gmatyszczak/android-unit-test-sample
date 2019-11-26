package pl.gmat.news.feature.main

import pl.gmat.news.feature.main.dao.NewsEntity
import javax.inject.Inject

interface NewsMapper {

    fun toNews(entity: NewsEntity): News
    fun toNewsEntity(news: News): NewsEntity
}

class NewsMapperImpl @Inject constructor() : NewsMapper {

    override fun toNews(entity: NewsEntity) = News(entity.id, entity.title, entity.body)

    override fun toNewsEntity(news: News) = NewsEntity(news.id, news.title, news.body)
}
