package com.example.lesson4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView

class ActorListAdapter: ListAdapter<ActorData, ActorListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ActorListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val actorImage: ImageView = itemView.findViewById(R.id.iv_downey)
        private val actorName: TextView = itemView.findViewById(R.id.tv_downey)

        fun bind(item: ActorData) {
            actorImage.setImageResource(item.image)
            actorName.text = item.name
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<ActorData>() {
        override fun areItemsTheSame(oldItem: ActorData, newItem: ActorData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ActorData, newItem: ActorData): Boolean {
            return oldItem == newItem
        }
    }
}

