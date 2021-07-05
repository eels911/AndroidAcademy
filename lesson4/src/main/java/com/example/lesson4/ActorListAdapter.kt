package com.example.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(context: Context,
                    var actor: List<Actor>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        return ViewHolderActor(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }
    //сэттим данные о вьюхолдер
    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(getItem(position))
    }

     class ViewHolderActor(v: View) : RecyclerView.ViewHolder(v) {
        //Do once
        private val image: ImageView = itemView.findViewById(R.id.iv_downey)
        private val genres: TextView = itemView.findViewById(R.id.tv_downey)
        //Do everytime
        fun bind(actor: Actor) {
            genres.text = actor.name
            image.setImageResource(actor.image)
        }
    }


}
