package com.alexyatsenka.polupokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.alexyatsenka.polupokemon.R
import com.alexyatsenka.polupokemon.presentation.di.Dagger
import com.alexyatsenka.polupokemon.presentation.ui.list.PokemonListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var systemBars : Insets? = null
    private val viewToSetInsets = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Dagger.buildComponent(applicationContext)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { _, insets ->
            systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            viewToSetInsets.forEach { setTopPadding(it) }
            viewToSetInsets.clear()
            insets
        }

        supportFragmentManager.commit {
            replace(
                R.id.container,
                PokemonListFragment.newInstance()
            )
            addToBackStack(null)
        }

    }

    @Synchronized
    fun setTopPadding(view : View) {
        if(systemBars != null) {
            view.setPadding(0 , systemBars!!.top, 0, 0)
        } else {
            viewToSetInsets += view
        }
    }
}