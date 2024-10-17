package com.alexyatsenka.polupokemon.domain.repo

import com.alexyatsenka.polupokemon.data.models.PokemonFullApi
import com.alexyatsenka.polupokemon.domain.models.Pokemon
import com.alexyatsenka.polupokemon.domain.models.PokemonFull

interface PokemonRepo {
    suspend fun getListPokemon() : List<Pokemon>
    suspend fun getDetailedPokemon(id: Int): PokemonFull
}