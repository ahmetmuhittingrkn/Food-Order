package com.example.foodapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.entity.CartItem
import com.example.foodapp.data.repo.FoodRepository
import com.example.foodapp.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val frepo:FoodRepository) : ViewModel() {

    var cartList = MutableLiveData<List<CartItem>>()

    fun getCartItems(kullanici_adi: String) {
        viewModelScope.launch {
            val response = frepo.getCartItems(kullanici_adi)
            if (response.isSuccessful) {
                val sepetYemekler = response.body()?.sepet_yemekler
                cartList.value = sepetYemekler ?: emptyList()
            } else {
                Log.e("CartViewModel", "Hata: ${response.message()}")
            }
        }
    }


    fun deleteFromCart(sepet_yemek_id:Int, kullanici_adi: String){
        viewModelScope.launch {
            frepo.deleteFromCart(sepet_yemek_id,kullanici_adi)
            getCartItems(kullanici_adi)
        }
    }
}