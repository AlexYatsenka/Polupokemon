package com.alexyatsenka.polupokemon.domain.usecase.getPokemonDetail

import com.alexyatsenka.polupokemon.data.models.PokemonFullApi
import com.alexyatsenka.polupokemon.domain.models.PokemonFull

interface GetPokemonDetailUseCase {
    suspend operator fun invoke(pokemonId : Int) : Result<PokemonFull>
}