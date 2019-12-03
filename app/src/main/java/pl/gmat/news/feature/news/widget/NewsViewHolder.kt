package pl.gmat.news.feature.news.widget

import androidx.recyclerview.widget.RecyclerView
import pl.gmat.news.common.model.News
import pl.gmat.news.databinding.ItemNewsBinding
import pl.gmat.news.feature.news.NewsViewModel

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val viewModel: NewsViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}