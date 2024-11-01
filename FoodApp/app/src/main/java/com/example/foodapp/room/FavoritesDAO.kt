package com.example.foodapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.foodapp.data.entity.Food

@Dao
interface FavoritesDAO {

    @Insert
    suspend fun addToFavorites(food: Food)

    @Delete
    suspend fun deleteFavorites(food: Food)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites() :List<Food>

}