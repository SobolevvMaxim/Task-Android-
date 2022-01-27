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
    ListAdapter<Image, PhotosAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val image: ImageButton = view.imageButton

        fun bind(item: Image, listener: RecyclerOnClickListener) {
            with(itemView) {
                image.setImageURI(item.uri)
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
    val clickListener: (chosenImage: Image) -> Unit
)

class DiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(old: Image, aNew: Image): Boolean {
        return old.uri == aNew.uri
    }

    override fun areContentsTheSame(old: Image, aNew: Image): Boolean {
        return old == aNew
    }
}