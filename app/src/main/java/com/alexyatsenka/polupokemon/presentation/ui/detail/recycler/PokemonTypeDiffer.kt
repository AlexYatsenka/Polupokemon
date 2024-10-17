package com.alexyatsenka.polupokemon.presentation.ui.detail.recycler

import androidx.recyclerview.widget.DiffUtil
import com.alexyatsenka.polupokemon.data.models.PokemonType

class PokemonTypeDiffer : DiffUtil.ItemCallback<PokemonType>() {
    override fun areItemsTheSame(oldItem: PokemonType, newItem: PokemonType) = oldItem == newItem
    override fun areContentsTheSame(oldItem: PokemonType, newItem: PokemonType) = areItemsTheSame(newItem, oldItem)
}