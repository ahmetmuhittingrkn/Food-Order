package com.example.foodapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapp.data.entity.Food

@Database(entities = [Food::class],version=1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDAO
}