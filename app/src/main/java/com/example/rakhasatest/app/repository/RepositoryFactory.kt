package com.example.rakhasatest.app.repository

import android.content.Context
import com.example.rakhasatest.etc.Factory
import com.example.rakhasatest.network.sources.PokemonDataSources


class PokemonRepositoryFactory(
    private val sources: PokemonDataSources
): Factory<PokemonRepository> {
    override fun create(context: Context): PokemonRepository {
        return PokemonRepository(sources)
    }
}