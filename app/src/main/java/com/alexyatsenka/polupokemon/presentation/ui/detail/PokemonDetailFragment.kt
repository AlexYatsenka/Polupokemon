package com.alexyatsenka.polupokemon.presentation.ui.detail

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Transition
import androidx.transition.Visibility
import com.alexyatsenka.polupokemon.R
import com.alexyatsenka.polupokemon.databinding.PokemonDetailFragmentBinding
import com.alexyatsenka.polupokemon.presentation.MainActivity
import com.alexyatsenka.polupokemon.presentation.di.Dagger
import com.alexyatsenka.polupokemon.presentation.ui.detail.recycler.PokemonTypeAdapter
import com.alexyatsenka.polupokemon.utils.firstUppercase
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.ExperimentalRequestListener
import com.bumptech.glide.request.RequestFutureTarget
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.transition.MaterialFade
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailFragment : Fragment() {

    private val pokemonId by lazy { requireArguments().getInt(POKEMON_ID) }
    private var mBinding : PokemonDetailFragmentBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { PokemonTypeAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Dagger.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = PokemonDetailFragmentBinding.inflate(inflater, container, false).apply {
            (requireActivity() as MainActivity).setTopPadding(llTitle)
            rvTypes.adapter = adapter
            ivBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        return binding.root
    }

    @Inject
    fun updateData(viewModelFactory : PokemonDetailViewModel.Factory) {
        lifecycleScope.launch {
            viewModelFactory.create(pokemonId).pokemon.collect { item ->
                if(item != null) {
                    adapter.submitList(item.type)
                    binding.apply {
                        if(item.fullImage != null) {
                            ivFront.visibility = VISIBLE
                            loadImage(item.fullImage!!)
                        } else {
                            ivFront.visibility = GONE
                        }

                        tvName.text = item.name.firstUppercase()
                        tvTypesTitle.visibility = VISIBLE
                        tvHeight.text = getString(R.string.height, item.height)
                        tvBaseExp.text = getString(R.string.base_exp, item.baseExperience)
                        tvAbilityTitle.text = getString(R.string.detail_ability, item.abilityTitle)
                        tvAbilityDescription.text = item.abilityDescription
                    }
                }
            }
        }
    }

    private fun PokemonDetailFragmentBinding.loadImage(model : String) {
        Glide.with(requireContext())
            .asBitmap()
            .load(model)
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean { return true }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any,
                    target: Target<Bitmap>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    setPalette(resource)
                    setImage(resource)
                    return true
                }
            })
            .into(ivFront)
    }
    private fun PokemonDetailFragmentBinding.setPalette(bitmap : Bitmap) {
        val palette = Palette.from(bitmap).generate()
        val animators = mutableListOf<ValueAnimator?>()
        val background = palette.dominantSwatch?.rgb
        val foreground = palette.dominantSwatch?.titleTextColor

        animators += background?.let { color ->
            llTitle.background = ResourcesCompat.getDrawable(
                root.resources,
                R.drawable.bg_pokemon_detail,
                null
            )
            ValueAnimator.ofArgb(Color.TRANSPARENT, color).apply {
                setDuration(250)
                addUpdateListener {
                    llTitle.backgroundTintList = ColorStateList.valueOf(
                        it.animatedValue as Int
                    )
                }
            }
        }

        animators += foreground?.let { color ->
            val currentColor = tvTitle.currentTextColor
            ValueAnimator.ofArgb(currentColor, color).apply {
                setDuration(250)
                addUpdateListener {
                    val newColor = it.animatedValue as Int
                    ivBack.imageTintList = ColorStateList.valueOf(newColor)
                    tvTitle.setTextColor(newColor)
                }
            }
        }

        animators.forEach { it?.start() }
    }
    private fun PokemonDetailFragmentBinding.setImage(bitmap: Bitmap) {
        ivFront.setAlpha(0f)
        ivFront.setImageBitmap(bitmap)
        ValueAnimator.ofFloat(0f, 1f).apply {
            setDuration(250)
            addUpdateListener { ivFront.setAlpha(1f) }
            start()
        }
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"
        fun newInstance(pokemonId: Int) = PokemonDetailFragment().also {
            it.arguments = bundleOf(
                POKEMON_ID to pokemonId
            )
        }
    }
}