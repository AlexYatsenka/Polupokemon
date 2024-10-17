package com.alexyatsenka.polupokemon.data.models

import com.alexyatsenka.polupokemon.PokemonListQuery
import com.alexyatsenka.polupokemon.domain.models.Pokemon

data class PokemonApi(
    override val id: Int,
    override val name: String,
    override val animatedImage: String,
    override val fullImage: String?,
    override val type: List<PokemonType>
) : Pokemon() {
    constructor(
        pokemonApi : PokemonListQuery.Pokemon
    ) : this(
        pokemonApi.id,
        pokemonApi.name,
        pokemonApi.image.first().front as String,
        null,
        pokemonApi.types.mapNotNull { it.type?.name?.let { PokemonType.fromName(it) } }
    )
}