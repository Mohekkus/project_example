package com.example.rakhasatest.etc

import androidx.appcompat.app.AppCompatActivity
import com.example.rakhasatest.PokemonApplication
import com.example.rakhasatest.app.repository.PokemonRepositoryFactory
import com.example.rakhasatest.database.Database

open class BaseActivity: AppCompatActivity() {

    val pokemonRepository by lazy {
        val container = (application as PokemonApplication).appContainer
        PokemonRepositoryFactory(container.pokemonSources)
            .create(this)
    }

    val pokemonDatabase by lazy {
        Database.getInstance(baseContext)
    }

}