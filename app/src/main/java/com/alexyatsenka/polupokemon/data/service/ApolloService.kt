package com.alexyatsenka.polupokemon.data.service

import com.alexyatsenka.polupokemon.PokemonFullQuery
import com.alexyatsenka.polupokemon.PokemonListQuery
import com.alexyatsenka.polupokemon.data.models.PokemonApi
import com.alexyatsenka.polupokemon.data.models.PokemonFullApi
import com.apollographql.apollo.ApolloClient
import javax.inject.Inject

class ApolloService @Inject constructor(
    private val client : ApolloClient
) {
    suspend fun getListPokemon() = client
        .query(PokemonListQuery())
        .execute()
        .dataOrThrow()
        .pokemons
        .map { PokemonApi(it) }

    suspend fun getPokemonFull(id : Int) = client
        .query(PokemonFullQuery(id))
        .execute()
        .dataOrThrow()
        .let { PokemonFullApi(id, it) }
}