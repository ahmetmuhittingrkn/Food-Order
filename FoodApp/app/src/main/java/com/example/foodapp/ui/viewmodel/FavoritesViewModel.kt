package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.entity.Food
import com.example.foodapp.data.repo.FavoritesRepository
import com.example.foodapp.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoritesRepository: FavoritesRepository) : ViewModel() {
    var favoriteList= MutableLiveData<List<Food>>()

    fun addtoFavorites(food: Food) {
        viewModelScope.launch {
            favoritesRepository.addToFavorites(food)
            updateFavorites()
        }
    }

    fun deleteFavorites(food:Food){
        viewModelScope.launch {
            favoritesRepository.deleteFavorites(food)
            updateFavorites()
        }
    }

    fun updateFavorites() {
        viewModelScope.launch {
            favoriteList.value=favoritesRepository.getAllFavorites()
        }
    }
}
