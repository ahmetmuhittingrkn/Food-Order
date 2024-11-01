package com.example.foodapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorites")
data class Food(@PrimaryKey val yemek_id:Int,
           val yemek_adi:String,
           val yemek_resim_adi:String,
           val yemek_fiyat:Int) : Serializable {
}