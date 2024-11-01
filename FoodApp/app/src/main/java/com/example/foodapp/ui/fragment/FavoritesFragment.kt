package com.example.foodapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.example.foodapp.ui.adapter.FavoritesAdapter
import com.example.foodapp.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel : FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesAdapter= FavoritesAdapter(emptyList(),viewModel)
        binding.recyclerViewFavorites.apply{
            adapter=favoritesAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }

        viewModel.updateFavorites()

        viewModel.favoriteList.observe(viewLifecycleOwner){ favoriteItems->
            favoritesAdapter.updateList(favoriteItems)
        }
    }
}