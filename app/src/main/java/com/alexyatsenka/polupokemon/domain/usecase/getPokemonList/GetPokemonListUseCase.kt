package com.alexyatsenka.polupokemon.domain.usecase.getPokemonList

import com.alexyatsenka.polupokemon.domain.models.Pokemon

interface GetPokemonListUseCase {
    suspend operator fun invoke() : Result<List<Pokemon>>
}