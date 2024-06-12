package com.example.rakhasatest.app.repository

import com.example.rakhasatest.network.sources.PokemonDataSources

class PokemonRepository(
    private val dataSources: PokemonDataSources
) {
    suspend fun getPokemonList(offset: Int) = dataSources.getList(offset)
    suspend fun getPokemon(id: String = "1") = dataSources.getDetail(id)
}