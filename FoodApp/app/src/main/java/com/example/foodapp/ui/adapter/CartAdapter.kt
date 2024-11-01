package com.example.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.entity.CartItem
import com.example.foodapp.databinding.ItemCartBinding
import com.example.foodapp.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar

class CartAdapter(var cartList: List<CartItem>,private val viewModel: CartViewModel): RecyclerView.Adapter<CartAdapter.CardHolder>() {
    inner class CardHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val binding=ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val carts=cartList[position]
        holder.binding.textViewYemekAdi.text=carts.yemek_adi
        holder.binding.textViewYemekFiyati.text="${carts.yemek_fiyat.toString()} ₺"
        holder.binding.textViewYemekAdeti.text="Adet : ${carts.yemek_siparis_adet.toString()}"
        holder.binding.textViewToplamFiyat.text="${carts.yemek_fiyat*carts.yemek_siparis_adet} ₺"

        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${carts.yemek_resim_adi}")
            .override(100, 100)
            .into(holder.binding.imageViewMeal)

        holder.binding.imageViewDelete.setOnClickListener {
            Snackbar.make(holder.itemView,"Silmek istediğinizden emin misiniz?",Snackbar.LENGTH_INDEFINITE)
                .setAction("EVET"){
                    viewModel.deleteFromCart(carts.sepet_yemek_id, "muhittin")
                }
                .show()
        }

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}