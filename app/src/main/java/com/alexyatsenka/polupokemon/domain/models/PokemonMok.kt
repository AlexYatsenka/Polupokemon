package com.alexyatsenka.polupokemon.domain.models

import com.alexyatsenka.polupokemon.data.models.PokemonType

data class PokemonMok(
    override val id: Int,
    override val name : String,
    override val animatedImage: String? = null,
    override val fullImage: String? = null,
    override val type: List<PokemonType> = emptyList()
) : Pokemon()