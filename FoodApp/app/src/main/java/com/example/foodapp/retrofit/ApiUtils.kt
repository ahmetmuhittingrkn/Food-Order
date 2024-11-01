package com.example.foodapp.retrofit

class ApiUtils {

    companion object {
        val BASE_URL="http://kasimadalan.pe.hu"

        fun getApiService() : ApiService {
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}