package com.alexyatsenka.polupokemon.domain.usecase.getPokemonList

import com.alexyatsenka.polupokemon.domain.models.Pokemon
import com.alexyatsenka.polupokemon.domain.models.PokemonMok
import com.alexyatsenka.polupokemon.domain.repo.PokemonRepo
import javax.inject.Inject

class GetPokemonListUseCaseImpl @Inject constructor(
    private val pokemonRepo: PokemonRepo
) : GetPokemonListUseCase {
    override suspend operator fun invoke() = runCatching {
        pokemonRepo.getListPokemon()
    }
}