package com.alexyatsenka.polupokemon.presentation.ui.list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexyatsenka.polupokemon.domain.models.Pokemon

class PokemonListAdapter(
    private val listener: PokemonListItemListener
) : ListAdapter<Pokemon, PokemonListViewHolder>(PokemonListDiffer()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder.newInstance(parent, listener)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

}