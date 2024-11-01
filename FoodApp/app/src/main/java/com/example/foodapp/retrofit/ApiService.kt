package com.example.foodapp.retrofit

import com.example.foodapp.data.entity.CartItemResponse
import com.example.foodapp.data.entity.CommonResponse
import com.example.foodapp.data.entity.FoodResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoodItems() : Response<FoodResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(@Field ("yemek_adi") yemek_adi:String,
                          @Field("yemek_resim_adi") yemek_resim_adi:String,
                          @Field("yemek_fiyat") yemek_fiyat:Int,
                          @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                          @Field("kullanici_adi") kullanici_adi:String ) : Response<CommonResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCartItems(@Field ("kullanici_adi") kullanici_adi: String) : Response<CartItemResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(@Field ("sepet_yemek_id") sepet_yemek_id:Int,
                               @Field("kullanici_adi") kullanici_adi: String) : Response<CommonResponse>

}