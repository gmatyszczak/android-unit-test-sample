package pl.gmat.news.feature.details.widget

import androidx.recyclerview.widget.RecyclerView
import pl.gmat.news.common.model.Comment
import pl.gmat.news.databinding.ItemCommentBinding

class CommentViewHolder(
    private val binding: ItemCommentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(comment: Comment) {
        binding.comment = comment
        binding.executePendingBindings()
    }
}