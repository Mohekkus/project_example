package com.example.rakhasatest.network

import com.example.rakhasatest.etc.Constant
import com.example.rakhasatest.network.services.PokemonService
import com.example.rakhasatest.network.sources.PokemonDataSources
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val logger = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .client(okClient)
        .build()

    val pokemonService = retrofit.create(PokemonService::class.java)

    val pokemonSources = PokemonDataSources(pokemonService)
}