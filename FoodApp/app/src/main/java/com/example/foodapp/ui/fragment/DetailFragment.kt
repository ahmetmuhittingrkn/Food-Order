package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()
    private var quantity=1
    private var unitPrice=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: DetailFragmentArgs by navArgs()
        val selectedFood=bundle.food

        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${selectedFood.yemek_resim_adi}")
            .override(500, 750)
            .into(binding.imageView)

        binding.textViewFoodName.text=selectedFood.yemek_adi
        binding.textViewPrice.text="${selectedFood.yemek_fiyat.toString()} ₺"

        unitPrice=selectedFood.yemek_fiyat

        binding.textViewUpdatedPrice.text=(unitPrice*quantity).toString()

        binding.buttonDecrease.setOnClickListener {
            if(quantity>1) {
                quantity--
                binding.textViewPiece.text=quantity.toString()
                updateTotalPrice()
            }
        }

        binding.buttonIncrease.setOnClickListener {
                quantity++
                binding.textViewPiece.text=quantity.toString()
                updateTotalPrice()
        }

        binding.buttonAddtoCart.setOnClickListener {
            viewModel.addToCart(selectedFood,quantity,"muhittin")
            Toast.makeText(context, "${quantity} adet ${selectedFood.yemek_adi} sepete eklendi!", Toast.LENGTH_SHORT).show()
            Log.d("DetailFragment", "Sepete eklenen yemek: ${selectedFood.yemek_adi} - Adet: $quantity")
        }

    }

        private fun updateTotalPrice() {
            val totalPrice= unitPrice*quantity
            binding.textViewUpdatedPrice.text="${totalPrice} ₺"
        }
}