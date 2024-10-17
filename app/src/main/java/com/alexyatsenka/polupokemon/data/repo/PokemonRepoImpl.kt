package com.alexyatsenka.polupokemon.data.repo

import com.alexyatsenka.polupokemon.data.service.ApolloService
import com.alexyatsenka.polupokemon.domain.repo.PokemonRepo

class PokemonRepoImpl(
    private val apiService : ApolloService
) : PokemonRepo {
    override suspend fun getListPokemon() = apiService.getListPokemon()
    override suspend fun getDetailedPokemon(id : Int) = apiService.getPokemonFull(id)
}