package com.example.rakhasatest

import android.app.Application
import com.example.rakhasatest.app.repository.PokemonRepository
import com.example.rakhasatest.app.repository.PokemonRepositoryFactory
import com.example.rakhasatest.database.Database
import com.example.rakhasatest.network.AppContainer

class PokemonApplication: Application() {

    val appContainer = AppContainer()
}