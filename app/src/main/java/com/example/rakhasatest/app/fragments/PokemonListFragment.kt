package com.example.rakhasatest.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rakhasatest.app.adapter.PokemonAdapter
import com.example.rakhasatest.databinding.LayoutPokemonListBinding
import com.example.rakhasatest.etc.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListFragment: BaseFragment() {

    private lateinit var binding: LayoutPokemonListBinding
    private lateinit var listener: RecyclerView.OnScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPokemon.apply {
            listener = object : RecyclerView.OnScrollListener() {
                private val VISIBLE_THRESHOLD = 15
                private var previousTotalItemCount = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layman = (layoutManager as LinearLayoutManager)
                    val totalItemCount = layman.itemCount
                    val lastVisibleItemPosition = layman.findLastVisibleItemPosition()

                    // Check if the dataset is invalidated, reset state
                    if (totalItemCount < previousTotalItemCount) {
                        this.previousTotalItemCount = totalItemCount
                        if (totalItemCount == 0) {
                            viewModel.setFetching(true)
                        }
                    }

                    // Check if loading is done and if the last visible item position is past the visible threshold
                    if (viewModel._fetching && (totalItemCount > previousTotalItemCount)) {
                        viewModel.setFetching(false)
                        previousTotalItemCount = totalItemCount
                    }

                    // Trigger load more when reaching the end of the list
                    if (!viewModel._fetching && (lastVisibleItemPosition + VISIBLE_THRESHOLD) > totalItemCount) {
                        viewModel.getPokemonList()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.rvPokemon.apply {
            layoutManager = GridLayoutManager(
                requireContext(), 2
            ).apply {
                isSmoothScrollbarEnabled = true
            }

            val pokemonAdapter = PokemonAdapter()
            adapter = pokemonAdapter
            addOnScrollListener(listener)

            lifecycleScope.launch {
                viewModel.pokemonList.observe(requireActivity()) {
                    if (it.isEmpty())
                        viewModel.getPokemonList()


                    CoroutineScope(Dispatchers.IO).launch {
                        pokemonAdapter.submitData(
                            PagingData.from(it)
                        )
                    }
                }
            }
        }
    }
}