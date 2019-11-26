package pl.gmat.news.feature.main.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.gmat.news.databinding.ItemNewsBinding
import pl.gmat.news.feature.main.MainViewModel
import pl.gmat.news.feature.main.News

private val diffUtil = object : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
}

class NewsAdapter(
    private val viewModel: MainViewModel
) :
    ListAdapter<News, NewsViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(getItem(position))
}