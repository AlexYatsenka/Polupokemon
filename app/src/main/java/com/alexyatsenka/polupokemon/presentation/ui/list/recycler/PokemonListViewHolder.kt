package com.alexyatsenka.polupokemon.presentation.ui.list.recycler

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.alexyatsenka.polupokemon.databinding.PokemonItemBinding
import com.alexyatsenka.polupokemon.domain.models.Pokemon
import com.bumptech.glide.Glide


class PokemonListViewHolder private constructor(
    private val binding: PokemonItemBinding,
    private val onItemListener: PokemonListItemListener
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun onBind(item : Pokemon) {
        binding.apply {
            Glide.with(root)
                .asGif()
                .load(item.animatedImage)
                .into(ivFront)


            root.setOnClickListener {
                onItemListener.onItemClick(item)
            }

            val color = ColorStateList.valueOf(
                root.resources.getColor(item.type.first().color, null)
            )

            tvPokemonType.backgroundTintList = color
            tvPokemonType.setText(item.type.first().title)
            tvPokemonName.text = item.name.replaceFirstChar(Char::titlecase)

            Palette.from(listOf(Palette.Swatch(color.defaultColor, 0)))
                .dominantSwatch?.let { tvPokemonType.setTextColor(it.titleTextColor) }
        }
    }

    companion object {
        fun newInstance(
            parent : ViewGroup,
            listener: PokemonListItemListener
        ) = PokemonListViewHolder(
            PokemonItemBinding.inflate(LayoutInflater.from(parent.context)),
            listener
        )
    }
}