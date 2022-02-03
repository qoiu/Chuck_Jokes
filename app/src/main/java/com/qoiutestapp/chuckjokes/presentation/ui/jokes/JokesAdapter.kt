package com.qoiutestapp.chuckjokes.presentation.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qoiutestapp.chuckjokes.databinding.JokeItemBinding

class JokesAdapter :
    RecyclerView.Adapter<JokesAdapter.JokesHolder>() {
    private var list: List<String> = emptyList()
    fun update(data: List<String>) {
        val util = DiffUtil.calculateDiff(DiffCallback(data))
        list = data
        util.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesHolder =
        JokesHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: JokesHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class JokesHolder(private val view: JokeItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(joke: String) {
            view.jokeItem.text = joke
        }
    }


    private inner class DiffCallback(private val data: List<String>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = list.size

        override fun getNewListSize(): Int = data.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            list[oldItemPosition] == data[newItemPosition]


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            list[oldItemPosition] == data[newItemPosition]
    }
}