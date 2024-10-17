package com.alexyatsenka.polupokemon.presentation.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexyatsenka.polupokemon.domain.models.Pokemon
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonList.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    private val mPokemonListFragment = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList = mPokemonListFragment.asSharedFlow()

    init {
        viewModelScope.launch {
            getPokemonListUseCase()
                .onSuccess { mPokemonListFragment.emit(it) }
                .onFailure { Log.e("Test", "Error: ", it) }

        }
    }

}