package com.example.foodapp.util

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.EOFException

class LenientGsonResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T?> {

    override fun convert(value: ResponseBody): T? {
        val jsonReader = gson.newJsonReader(value.charStream())
        jsonReader.isLenient = true
        return try {
            adapter.read(jsonReader)
        } catch (e: EOFException) {
            // Boş yanıt durumunda null döndür
            null
        } finally {
            value.close()
        }
    }
}
