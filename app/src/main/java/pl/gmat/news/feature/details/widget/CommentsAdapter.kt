package pl.gmat.news.feature.details.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.gmat.news.common.model.Comment
import pl.gmat.news.databinding.ItemCommentBinding

private val diffUtil = object : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment) = oldItem == newItem
}

class CommentsAdapter : ListAdapter<Comment, CommentViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommentViewHolder(
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) =
        holder.bind(getItem(position))
}