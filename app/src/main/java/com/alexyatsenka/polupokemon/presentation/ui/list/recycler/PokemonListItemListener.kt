package com.alexyatsenka.polupokemon.presentation.ui.list.recycler

import com.alexyatsenka.polupokemon.domain.models.Pokemon

fun interface PokemonListItemListener {
    fun onItemClick(item : Pokemon)
}