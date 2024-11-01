package com.example.foodapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.entity.Food
import com.example.foodapp.data.repo.FoodRepository
import com.example.foodapp.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val frepo: FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Food>>()

    init {
        getAllFoods()
    }

    fun getAllFoods() {
        viewModelScope.launch {
            val response=frepo.getAllFoods()
            if(response.isSuccessful) {
                foodList.value = response.body()?.yemekler
            }else{
                Log.e("HomeViewModel", "Error: ${response.errorBody()?.string()}")
            }
        }
    }
}


