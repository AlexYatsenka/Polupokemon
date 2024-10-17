package com.alexyatsenka.polupokemon.presentation.ui.detail.recycler

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.alexyatsenka.polupokemon.data.models.PokemonType
import com.alexyatsenka.polupokemon.databinding.PokemonTypeItemBinding

class PokemonTypeViewHolder private constructor(
    private val binding : PokemonTypeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item : PokemonType) {
        binding.apply {
            val color = ColorStateList.valueOf(
                root.resources.getColor(item.color, null)
            )

            root.backgroundTintList = color
            root.setText(item.title)
            Palette.from(listOf(Palette.Swatch(color.defaultColor, 0)))
                .dominantSwatch?.let { root.setTextColor(it.titleTextColor) }
        }
    }

    companion object {
        fun newInstance(parent : ViewGroup) = PokemonTypeViewHolder(
            PokemonTypeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}