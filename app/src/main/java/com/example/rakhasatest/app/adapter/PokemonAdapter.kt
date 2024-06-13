package com.example.rakhasatest.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rakhasatest.database.room.PokemonEntity
import com.example.rakhasatest.databinding.ItemPokemonListBinding

class PokemonAdapter(
): PagingDataAdapter<PokemonEntity, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonEntity>() {
            override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemPokemonListBinding
                .inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ItemViewHolder)
            .bind(
                getItem(position) ?: return
            )
    }

    inner class ItemViewHolder(private val binding: ItemPokemonListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonEntity) {
            binding.apply {
                tvPokemonName.text = item.name
                imgPokemon.load(item.imageUrl?.frontDefault)
            }
        }
    }
}