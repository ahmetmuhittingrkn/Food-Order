package com.example.foodapp.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class LenientGsonConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): LenientGsonConverterFactory {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return LenientGsonConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        return LenientGsonResponseBodyConverter(gson, adapter)
    }
}

