package com.alexyatsenka.polupokemon.presentation.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.alexyatsenka.polupokemon.R
import com.alexyatsenka.polupokemon.databinding.PokemonListFragmentBinding
import com.alexyatsenka.polupokemon.domain.models.Pokemon
import com.alexyatsenka.polupokemon.presentation.MainActivity
import com.alexyatsenka.polupokemon.presentation.di.Dagger
import com.alexyatsenka.polupokemon.presentation.ui.detail.PokemonDetailFragment
import com.alexyatsenka.polupokemon.presentation.ui.list.recycler.GifPreloadModelProvider
import com.alexyatsenka.polupokemon.presentation.ui.list.recycler.PokemonListAdapter
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFragment : Fragment() {

    @Inject lateinit var viewModel : PokemonListViewModel

    private var mBinding : PokemonListFragmentBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { PokemonListAdapter(::onItemClick) }
    private val preloadModelProvider by lazy { GifPreloadModelProvider(requireContext()) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Dagger.appComponent.inject(this)
        lifecycleScope.launch {
            viewModel.pokemonList.collect {
                preloadModelProvider.items = it.map { it.animatedImage ?: "" }
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
            (requireActivity() as MainActivity).setTopPadding(root)
            rvPokemons.adapter = adapter
            rvPokemons.addOnScrollListener(
                RecyclerViewPreloader(
                    requireActivity(),
                    preloadModelProvider,
                    FixedPreloadSizeProvider(45, 45),
                    15
                )
            )
        }
        return binding.root
    }

    private fun onItemClick(item : Pokemon) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(
                R.id.container,
                PokemonDetailFragment.newInstance(item.id)
            )
            addToBackStack(null)
        }
    }

    companion object {
        fun newInstance() = PokemonListFragment()
    }
}