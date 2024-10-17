package com.alexyatsenka.polupokemon.presentation.ui.list.recycler

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder

class GifPreloadModelProvider(
    private val context : Context,
    var items : List<String> = emptyList()
) : PreloadModelProvider<String> {
    override fun getPreloadItems(position: Int): List<String> {
        val url = items[position]
        if (url.isEmpty()) {
            return emptyList()
        }
        return listOf(url)
    }

    override fun getPreloadRequestBuilder(item: String): RequestBuilder<*> {
        return Glide.with(context)
            .asGif()
            .load(item)
    }
}