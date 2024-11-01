package com.example.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.entity.Food
import com.example.foodapp.databinding.ItemFavoriteBinding
import com.example.foodapp.ui.viewmodel.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesAdapter(private var  favoriteList: List<Food>, private val viewModel: FavoritesViewModel) : RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {

    inner class FavoritesHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        val binding= ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        val favList= favoriteList[position]
        holder.binding.textViewFoodName.text=favList.yemek_adi
        holder.binding.textViewFoodPrice.text="${favList.yemek_fiyat} ₺"

        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${favList.yemek_resim_adi}")
            .into(holder.binding.imageViewFood)

        holder.binding.imageViewDelete.setOnClickListener {
            Snackbar.make(holder.itemView,"Silmek istediğinizden emin misiniz?",Snackbar.LENGTH_INDEFINITE)
                .setAction("EVET"){
                    viewModel.deleteFavorites(favList)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateList(newList: List<Food>) {
        favoriteList = newList
        notifyDataSetChanged()
    }
}