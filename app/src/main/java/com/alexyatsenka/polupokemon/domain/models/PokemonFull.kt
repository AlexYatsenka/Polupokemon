package com.alexyatsenka.polupokemon.domain.models

abstract class PokemonFull : Pokemon() {
    abstract val height : Int?
    abstract val baseExperience : Int?
    abstract val abilityTitle : String
    abstract val abilityDescription : String
}