package pl.gmat.news.feature.main.widget

import androidx.recyclerview.widget.RecyclerView
import pl.gmat.news.databinding.ItemNewsBinding
import pl.gmat.news.feature.main.MainViewModel
import pl.gmat.news.feature.main.News

class NewsViewHolder(
    private val binding: ItemNewsBinding,
    private val viewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}