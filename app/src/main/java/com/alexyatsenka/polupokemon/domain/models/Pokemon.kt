package com.alexyatsenka.polupokemon.domain.models

import com.alexyatsenka.polupokemon.data.models.PokemonType

abstract class Pokemon {
    abstract val id : Int
    abstract val name : String
    abstract val animatedImage : String?
    abstract val fullImage : String?
    abstract val type : List<PokemonType>
}