package com.example.rakhasatest.etc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rakhasatest.PokemonApplication
import com.example.rakhasatest.app.MainActivity
import com.example.rakhasatest.app.repository.PokemonRepository
import com.example.rakhasatest.app.repository.PokemonRepositoryFactory
import com.example.rakhasatest.app.viewmodel.PokemonViewModelFactory
import com.example.rakhasatest.database.Database

open class BaseFragment: Fragment() {

//    val pokemonRepository by lazy {
//        val container = (activity?.application as PokemonApplication).appContainer
//        PokemonRepositoryFactory(container.pokemonSources)
//            .create(context)
//    }
//
//    val pokemonDatabase by lazy {
//        Database.getInstance(context)
//    }
//
//    val viewModel by lazy {
//        PokemonViewModelFactory(
//            pokemonRepository, pokemonDatabase
//        ).create(this)
//    }

    private val pokemonRepository by lazy {
        (activity as MainActivity).pokemonRepository
    }
    private val pokemonDatabase by lazy {
        (activity as MainActivity).pokemonDatabase
    }
    val viewModel by lazy {
        PokemonViewModelFactory(
            pokemonRepository, pokemonDatabase
        ).create(requireContext())
    }

}