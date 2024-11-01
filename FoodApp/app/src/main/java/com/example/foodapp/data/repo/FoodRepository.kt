package com.example.foodapp.data.repo

import com.example.foodapp.data.entity.CartItem
import com.example.foodapp.retrofit.ApiService
import javax.inject.Inject

class FoodRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getAllFoods() = apiService.getFoodItems()

    suspend fun addToCart(cartItem: CartItem) = apiService.addToCart(
        cartItem.yemek_adi,
        cartItem.yemek_resim_adi,
        cartItem.yemek_fiyat,
        cartItem.yemek_siparis_adet,
        cartItem.kullanici_adi
    )

    suspend fun getCartItems(kullanici_adi:String) = apiService.getCartItems(kullanici_adi)

    suspend fun deleteFromCart(sepet_yemek_id:Int, kullanici_adi: String) =
        apiService.deleteFromCart(sepet_yemek_id,kullanici_adi)

}