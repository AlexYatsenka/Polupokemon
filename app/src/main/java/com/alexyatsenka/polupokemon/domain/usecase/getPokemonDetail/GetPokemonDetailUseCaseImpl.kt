package com.alexyatsenka.polupokemon.domain.usecase.getPokemonDetail

import com.alexyatsenka.polupokemon.domain.repo.PokemonRepo
import javax.inject.Inject

class GetPokemonDetailUseCaseImpl @Inject constructor(
    private val pokemonRepo: PokemonRepo
) : GetPokemonDetailUseCase {
    override suspend fun invoke(pokemonId: Int) = runCatching {
        pokemonRepo.getDetailedPokemon(pokemonId)
    }
}