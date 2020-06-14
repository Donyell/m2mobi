package nl.donyell.m2mobi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.donyell.m2mobi.databinding.ListItemCommentBinding
import nl.donyell.m2mobi.domain.models.Comment

class CommentAdapter : ListAdapter<Comment, RecyclerView.ViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentViewHolder(
            ListItemCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as CommentViewHolder).bind(plant)
    }

    class CommentViewHolder(private val binding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.apply {
                comment = item
                executePendingBindings()
            }
        }
    }
}

private class CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Comment,
        newItem: Comment
    ): Boolean {
        return oldItem == newItem
    }
}