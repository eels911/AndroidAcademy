package com.example.lesson5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson4.R

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
            image.setImageResource(actor.image)
        }
    }
}


