package com.example.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.entity.Food
import com.example.foodapp.databinding.CardDesignBinding
import com.example.foodapp.ui.fragment.HomeFragmentDirections
import com.example.foodapp.ui.viewmodel.FavoritesViewModel

class HomeAdapter(var foodList: List<Food>,private val viewModel: FavoritesViewModel) : RecyclerView.Adapter<HomeAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(val binding: CardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding= CardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foods=foodList[position]
        holder.binding.textViewYemekAdi.text=foods.yemek_adi
        holder.binding.textViewYemekFiyati.text="${foods.yemek_fiyat.toString()} ₺"

        val url="http://kasimadalan.pe.hu/yemekler/resimler/${foods.yemek_resim_adi}"
        Glide.with(holder.itemView.context).load(url).override(500,750).into(holder.binding.imageViewYemek)

        holder.binding.imageViewYemek.setOnClickListener {
            val gecis=HomeFragmentDirections.actionHomeFragmentToDetailFragment(foods)
            Navigation.findNavController(holder.itemView).navigate(gecis)
        }

        holder.binding.imageViewFavorite.setOnClickListener {
            viewModel.addtoFavorites(foods)
            Toast.makeText(holder.itemView.context,"${foods.yemek_adi} favorilerinize eklendi!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
       return foodList.size
    }
}