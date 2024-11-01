package com.example.foodapp.retrofit

import com.example.foodapp.util.LenientGsonConverterFactory
import retrofit2.Retrofit

class RetrofitClient {

    companion object {
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(LenientGsonConverterFactory.create()) // Burada LenientGsonConverterFactory kullanıyoruz
                .build()
        }
    }
}
