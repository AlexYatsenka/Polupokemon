package com.alexyatsenka.polupokemon.presentation.di

import android.content.Context
import com.alexyatsenka.polupokemon.data.service.ApolloService
import com.alexyatsenka.polupokemon.presentation.ui.detail.PokemonDetailFragment
import com.alexyatsenka.polupokemon.presentation.ui.detail.PokemonDetailViewModel
import com.alexyatsenka.polupokemon.presentation.ui.list.PokemonListFragment
import com.alexyatsenka.polupokemon.presentation.ui.list.PokemonListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(fragment : PokemonListFragment)
    fun inject(fragment: PokemonDetailFragment)

    fun getPokemonListViewModel() : PokemonListViewModel
    fun getPokemonDetailViewModel() : PokemonDetailViewModel.Factory

    fun provideApollo() : ApolloService

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context : Context
        ) : AppComponent
    }
}