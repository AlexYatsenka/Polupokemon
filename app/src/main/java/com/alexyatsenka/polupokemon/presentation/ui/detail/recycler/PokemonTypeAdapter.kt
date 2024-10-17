package com.alexyatsenka.polupokemon.presentation.ui.detail.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexyatsenka.polupokemon.data.models.PokemonType

class PokemonTypeAdapter : ListAdapter<PokemonType, PokemonTypeViewHolder>(PokemonTypeDiffer()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PokemonTypeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}