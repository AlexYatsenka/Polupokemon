package com.alexyatsenka.polupokemon.presentation.di

import com.alexyatsenka.polupokemon.data.repo.PokemonRepoImpl
import com.alexyatsenka.polupokemon.data.service.ApolloService
import com.alexyatsenka.polupokemon.domain.repo.PokemonRepo
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonDetail.GetPokemonDetailUseCase
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonDetail.GetPokemonDetailUseCaseImpl
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonList.GetPokemonListUseCaseImpl
import com.alexyatsenka.polupokemon.domain.usecase.getPokemonList.GetPokemonListUseCase
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.internal.ApolloClientListener
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class AppModule {
    @Provides
    fun provideApolloClient() = ApolloClient.Builder()
        .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
        .build()

    @Provides
    fun providePokemonRepo(apiService : ApolloService) : PokemonRepo = PokemonRepoImpl(apiService)

    @Provides
    fun provideGetPokemonListUseCase(repo : PokemonRepo) : GetPokemonListUseCase = GetPokemonListUseCaseImpl(repo)

    @Provides
    fun provideGetPokemonDetailUseCase(repo : PokemonRepo) : GetPokemonDetailUseCase = GetPokemonDetailUseCaseImpl(repo)
}