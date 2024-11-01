package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.entity.CartItem
import com.example.foodapp.data.entity.Food
import com.example.foodapp.data.repo.FoodRepository
import com.example.foodapp.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val frepo: FoodRepository): ViewModel() {

    fun addToCart(food: Food, quantity:Int, kullanici_adi:String) {
        viewModelScope.launch {
            val tempCartItem=CartItem(0,
                food.yemek_adi,
                food.yemek_resim_adi,
                food.yemek_fiyat,
                quantity,
                kullanici_adi)

            frepo.addToCart(tempCartItem)
        }
    }
}