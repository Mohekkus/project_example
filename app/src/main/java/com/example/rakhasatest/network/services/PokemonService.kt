package com.example.rakhasatest.network.services

import com.example.rakhasatest.app.model.PokemonDetailResponse
import com.example.rakhasatest.app.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon?")
    suspend fun getPokemonList(
        @Query(value = "offset") offset: Int = 0,
        @Query(value = "limit") limit: Int = 20
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path(value = "id") id: String): Response<PokemonDetailResponse>

}