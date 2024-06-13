package com.example.rakhasatest.app.viewmodel

import android.content.Context
import com.example.rakhasatest.app.repository.PokemonRepository
import com.example.rakhasatest.database.AppDatabase
import com.example.rakhasatest.etc.Factory

class PokemonViewModelFactory(
    private val pokemonRepository: PokemonRepository,
    private val pokemonDatabase: AppDatabase
): Factory<PokemonViewModel> {
    override fun create(context: Context): PokemonViewModel {
        return PokemonViewModel(pokemonRepository, pokemonDatabase)
    }
}