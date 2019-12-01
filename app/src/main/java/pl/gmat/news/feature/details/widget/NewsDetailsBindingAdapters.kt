package pl.gmat.news.feature.details.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.gmat.news.common.model.Comment

@BindingAdapter("comments")
fun setComments(recyclerView: RecyclerView, comments: List<Comment>?) {
    (recyclerView.adapter as? CommentsAdapter)?.submitList(comments)
}