package com.alexyatsenka.polupokemon.data.models

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.alexyatsenka.polupokemon.R

enum class PokemonType(
    @StringRes val title : Int,
    @ColorRes val color: Int
) {
    NORMAL(R.string.type_normal, R.color.type_normal),
    FIGHTING(R.string.type_fighting, R.color.type_fighting),
    FLYING(R.string.type_flying, R.color.type_flying),
    POISON(R.string.type_poison, R.color.type_poison),
    GROUND(R.string.type_ground, R.color.type_ground),
    ROCK(R.string.type_rock, R.color.type_rock),
    BUG(R.string.type_bug, R.color.type_bug),
    GHOST(R.string.type_ghost, R.color.type_ghost),
    STEEL(R.string.type_steel, R.color.type_steel),
    FIRE(R.string.type_fire, R.color.type_fire),
    WATER(R.string.type_water, R.color.type_water),
    GRASS(R.string.type_grass, R.color.type_grass),
    ELECTRIC(R.string.type_electric, R.color.type_electric),
    PSYCHIC(R.string.type_psychic, R.color.type_phychic),
    ICE(R.string.type_ice, R.color.type_ice),
    DRAGON(R.string.type_dragon, R.color.type_dragon),
    DARK(R.string.type_dark, R.color.type_dark),
    FAIRY(R.string.type_fairy, R.color.type_fairy),
    STELLAR(R.string.type_stellar, R.color.type_stellar),
    UNKNOWN(R.string.type_unknown, R.color.type_unknown),
    SHADOW(R.string.type_shadow, R.color.type_shadow);

    companion object {
        fun fromName(name: String?): PokemonType {
            return entries.find { it.name.equals(name, ignoreCase = true) } ?: UNKNOWN
        }
    }
}
