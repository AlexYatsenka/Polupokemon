package com.alexyatsenka.polupokemon.utils

fun String.firstUppercase() = run  {
    StringBuilder(this).apply {
        set(0, get(0).uppercaseChar())
    }.toString()
}