package com.example.rakhasatest.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rakhasatest.app.repository.PokemonRepository
import com.example.rakhasatest.database.AppDatabase
import com.example.rakhasatest.database.room.PokemonEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository,
    private val pokemonDatabase: AppDatabase
): ViewModel() {

    private var _pokemonList = MutableLiveData<MutableList<PokemonEntity>>()
    val pokemonList: LiveData<MutableList<PokemonEntity>> = _pokemonList

    var _fetching = false

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _pokemonList.postValue(
                pokemonDatabase.pokemonDao().getAll().toMutableList()
            )
        }
    }

    fun getPokemonList() {
       CoroutineScope(Dispatchers.IO).launch {
            if (_fetching) return@launch
           setFetching(true)

            val offset =
                pokemonDatabase.pokemonDao().count()

            val response = pokemonRepository.getPokemonList(
                offset
            )

            if (!response.isSuccessful || response.body() == null) return@launch

            val urlMap = mutableListOf<String?>()

            response.body()?.results?.map {
                urlMap.add(it?.url)
            }

            val deferredCall = urlMap.map {
                if (it?.isEmpty() == true) return@launch

                val last = it?.substringBeforeLast("/")
                val index = last?.substringAfterLast("/")

                async(Dispatchers.IO) {
                    getDetailedPokemon(index.toString())
                }
            }


            deferredCall.awaitAll().apply {
                async {
                    pokemonDatabase.pokemonDao().insertAll(
                        filterNotNull()
                    )
                }.await()

                async {
                    _pokemonList.postValue(
                        pokemonDatabase.pokemonDao().getAll().toMutableList()
                    )
                }.await()
            }
        }
    }

    fun setFetching(boolean: Boolean) {
        _fetching = boolean
    }

    private suspend fun getDetailedPokemon(indexDetailItem: String): PokemonEntity? {
        val response = pokemonRepository.getPokemon(indexDetailItem)
        if (!response.isSuccessful || response.body() == null) return null

        response.body()
            ?.let {
                if (it.name.isNullOrEmpty() || it.sprites == null) return null

                return PokemonEntity(indexDetailItem.toInt(), it.name, it.sprites)
            }

        return null
    }
}