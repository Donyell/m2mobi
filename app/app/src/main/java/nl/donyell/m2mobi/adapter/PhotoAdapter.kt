package nl.donyell.m2mobi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.donyell.m2mobi.MainFragmentDirections
import nl.donyell.m2mobi.databinding.ListItemPhotoBinding
import nl.donyell.m2mobi.response.GetPhotosResponse

class PhotoAdapter : ListAdapter<GetPhotosResponse, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder(
            ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PhotoViewHolder).bind(plant)
    }

    class PhotoViewHolder(private val binding: ListItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.photo?.let { photo ->
                    navigateToPhoto(photo, it)
                }
            }
        }

        private fun navigateToPhoto(photo: GetPhotosResponse, view: View) {
            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(photo.id)
            view.findNavController().navigate(direction)
        }

        fun bind(item: GetPhotosResponse) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class PhotoDiffCallback : DiffUtil.ItemCallback<GetPhotosResponse>() {

    override fun areItemsTheSame(oldItem: GetPhotosResponse, newItem: GetPhotosResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetPhotosResponse,
        newItem: GetPhotosResponse
    ): Boolean {
        return oldItem == newItem
    }
}