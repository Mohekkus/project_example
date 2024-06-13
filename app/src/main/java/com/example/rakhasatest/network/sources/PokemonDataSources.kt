package com.example.rakhasatest.network.sources

import com.example.rakhasatest.network.services.PokemonService

class PokemonDataSources(
    private val pokemonService: PokemonService
) {

    suspend fun getList(offset: Int) = pokemonService.getPokemonList(offset)

    suspend fun getDetail(id: String) = pokemonService.getPokemonDetails(id)
}