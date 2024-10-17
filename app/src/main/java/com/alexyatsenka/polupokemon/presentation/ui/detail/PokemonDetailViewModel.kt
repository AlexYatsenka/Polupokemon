package com.alexyatsenka.polupokemon.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexyatsenka.polupokemon.domain.models.PokemonFull
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonDetail.GetPokemonDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel @AssistedInject constructor(
    getPokemonDetailUseCase: GetPokemonDetailUseCase,
    @Assisted pokemonId : Int
) : ViewModel() {

    init {
        viewModelScope.launch {
            getPokemonDetailUseCase(pokemonId)
                .onSuccess { mPokemon.emit(it) }
                .onFailure { Log.e("Test", "Error: ", it) }
        }
    }

    private val mPokemon = MutableStateFlow<PokemonFull?>(null)
    val pokemon = mPokemon.asStateFlow()

    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int) : PokemonDetailViewModel
    }
}