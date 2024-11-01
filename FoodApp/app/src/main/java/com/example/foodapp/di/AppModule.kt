package com.example.foodapp.di

import android.content.Context
import androidx.room.Room
import com.example.foodapp.data.repo.FoodRepository
import com.example.foodapp.retrofit.ApiService
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.room.AppDatabase
import com.example.foodapp.room.FavoritesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiUtils.getApiService()
    }

    @Provides
    @Singleton
    fun provideFoodRepository(apiService: ApiService): FoodRepository {
        return FoodRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "food_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoritesDAO(appDatabase: AppDatabase): FavoritesDAO {
        return appDatabase.favoritesDao()
    }
}
