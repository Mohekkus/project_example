package com.example.rakhasatest.app

import android.os.Bundle
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rakhasatest.PokemonApplication
import com.example.rakhasatest.app.adapter.PokemonAdapter
import com.example.rakhasatest.app.repository.PokemonRepositoryFactory
import com.example.rakhasatest.app.viewmodel.PokemonViewModelFactory
import com.example.rakhasatest.database.Database
import com.example.rakhasatest.databinding.LayoutMainBinding
import com.example.rakhasatest.etc.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity: BaseActivity() {

    private val viewmodel by lazy {
        PokemonViewModelFactory(
            pokemonRepository, pokemonDatabase
        ).create(this)
    }

    private val binding by lazy {
        LayoutMainBinding.inflate(layoutInflater)
    }

    private lateinit var listener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.rvPokemon.apply {
//            listener = object : RecyclerView.OnScrollListener() {
//                private val VISIBLE_THRESHOLD = 12
//                private var previousTotalItemCount = 0
//
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//
//                    val layman = (layoutManager as LinearLayoutManager)
//                    val totalItemCount = layman.itemCount
//                    val lastVisibleItemPosition = layman.findLastVisibleItemPosition()
//
//                    // Check if the dataset is invalidated, reset state
//                    if (totalItemCount < previousTotalItemCount) {
//                        this.previousTotalItemCount = totalItemCount
//                        if (totalItemCount == 0) {
//                            viewmodel.setFetching(true)
//                        }
//                    }
//
//                    // Check if loading is done and if the last visible item position is past the visible threshold
//                    if (viewmodel._fetching && (totalItemCount > previousTotalItemCount)) {
////                        loading = false
//                        viewmodel.setFetching(false)
//                        previousTotalItemCount = totalItemCount
//                    }
//
//                    // Trigger load more when reaching the end of the list
//                    if (!viewmodel._fetching && (lastVisibleItemPosition + VISIBLE_THRESHOLD) > totalItemCount) {
//                        viewmodel.getPokemonList()
//                    }
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
//        binding.rvPokemon.apply {
//            layoutManager = LinearLayoutManager(
//                this@MainActivity, LinearLayoutManager.VERTICAL, false
//            ).apply {
//                isSmoothScrollbarEnabled = true
//            }
//
//            val pokemonAdapter = PokemonAdapter()
//            adapter = pokemonAdapter
//
//            lifecycleScope.launch {
//                viewmodel.pokemonList.asFlow().collectLatest {
//                    if (it.isEmpty())
//                        viewmodel.getPokemonList()
//
//                    removeOnScrollListener(listener)
//
//
//                    pokemonAdapter.submitData(
//                        PagingData.from(it)
//                    )
//
//                    if (it.isNotEmpty())
//                        addOnScrollListener(listener)
//                }
//            }
//        }
    }
}