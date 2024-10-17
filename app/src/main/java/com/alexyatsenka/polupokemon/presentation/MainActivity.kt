package com.alexyatsenka.polupokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.alexyatsenka.polupokemon.R
import com.alexyatsenka.polupokemon.presentation.di.Dagger
import com.alexyatsenka.polupokemon.presentation.ui.list.PokemonListFragment

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
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
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