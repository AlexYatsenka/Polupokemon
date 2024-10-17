package com.alexyatsenka.polupokemon.data.models

import com.alexyatsenka.polupokemon.PokemonFullQuery
import com.alexyatsenka.polupokemon.domain.models.PokemonFull

data class PokemonFullApi(
    override val id: Int,
    override val name: String,
    override val height: Int?,
    override val baseExperience: Int?,
    override val animatedImage: String?,
    override val fullImage: String?,
    override val type: List<PokemonType>,
    override val abilityTitle: String,
    override val abilityDescription: String
) : PokemonFull() {
    constructor(
        id: Int,
        pokemonApi: PokemonFullQuery.Data
    ) : this(
        id,
        pokemonApi.info.first().name,
        pokemonApi.info.first().height,
        pokemonApi.info.first().base_experience,
        null,
        pokemonApi.image.first().front as String,
        pokemonApi.info.first().types.mapNotNull {
            it.type?.name?.let { PokemonType.fromName(it) }
        },
        pokemonApi.ability.first().title.first().name,
        pokemonApi.ability.first().description.first().main
    )
}