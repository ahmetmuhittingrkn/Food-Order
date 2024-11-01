package com.example.foodapp.data.repo

import androidx.lifecycle.LiveData
import com.example.foodapp.data.entity.Food
import com.example.foodapp.room.FavoritesDAO
import javax.inject.Inject

class FavoritesRepository @Inject constructor(val favoritesDAO: FavoritesDAO) {

    suspend fun getAllFavorites() :List<Food> = favoritesDAO.getAllFavorites()

    suspend fun addToFavorites(food:Food) = favoritesDAO.addToFavorites(food)

    suspend fun deleteFavorites(food: Food) = favoritesDAO.deleteFavorites(food)
}