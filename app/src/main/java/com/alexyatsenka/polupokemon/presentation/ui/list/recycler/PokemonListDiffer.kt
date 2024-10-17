package com.alexyatsenka.polupokemon.presentation.ui.list.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.alexyatsenka.polupokemon.domain.models.Pokemon

class PokemonListDiffer : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
}