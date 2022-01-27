package com.example.taskandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class PhotosAdapter(private val listener: RecyclerOnClickListener) :
    ListAdapter<ImageItem, PhotosAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val image: ImageButton = view.imageButton

        fun bind(item: ImageItem, listener: RecyclerOnClickListener) {
            with(itemView) {
                image.setImageBitmap(item.bitmap)
                setOnClickListener {
                    listener.clickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener = listener)
    }
}

class RecyclerOnClickListener(
    val clickListener: (chosenImage: ImageItem) -> Unit
)

class DiffCallback : DiffUtil.ItemCallback<ImageItem>() {
    override fun areItemsTheSame(old: ImageItem, aNew: ImageItem): Boolean {
        return old.id == aNew.id
    }

    override fun areContentsTheSame(old: ImageItem, aNew: ImageItem): Boolean {
        return old == aNew
    }
}