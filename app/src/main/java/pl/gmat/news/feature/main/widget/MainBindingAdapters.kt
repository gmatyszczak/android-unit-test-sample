package pl.gmat.news.feature.main.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.gmat.news.feature.main.News

@BindingAdapter("news")
fun setNews(recyclerView: RecyclerView, news: List<News>?) {
    (recyclerView.adapter as? NewsAdapter)?.submitList(news)
}