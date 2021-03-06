package com.example.lesson8.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson8.R
import com.example.lesson8.model.Actor

class ActorListAdapter(context: Context) : RecyclerView.Adapter<ActorListAdapter.ViewHolderActor>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var actors: List<Actor> = listOf()
    
    fun setList(newActors: List<Actor>) {
        this.actors = newActors
        notifyDataSetChanged()
    }

    //сколько элементов списке
    override fun getItemCount(): Int = actors.size
    //по позиции возвращаем данные об актере
    private fun getItem(position: Int): Actor = actors[position]

    //создаем холдер передавая вьюшку из ресурса
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        val itemLayout=inflater.inflate(R.layout.item_actor, parent, false)
        return ViewHolderActor(itemLayout)
    }
    //сэттим данные о вьюхолдер
    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolderActor(v: View) : RecyclerView.ViewHolder(v) {
        //Do once
        private val image: ImageView = itemView.findViewById(R.id.iv_actor)
        private val genres: TextView = itemView.findViewById(R.id.tv_actor_name)
        //Do everytime
        fun bind(actor: Actor) {
            genres.text = actor.name
            val requestOptions = RequestOptions().apply{
                transform(CenterCrop(), RoundedCorners(20))
            }
            Glide.with(image.context)
                .load(actor.imageUrl)
                .apply(requestOptions)
                .into(image)
        }
    }
}


