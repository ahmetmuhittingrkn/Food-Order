package com.example.foodapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.data.entity.CartItem
import com.example.foodapp.databinding.FragmentCardBinding
import com.example.foodapp.ui.adapter.CartAdapter
import com.example.foodapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter= CartAdapter(emptyList(),viewModel)

        binding.recyclerViewCart.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=cartAdapter
        }

        viewModel.getCartItems("muhittin")

        binding.buttonAccept.setOnClickListener {
            Toast.makeText(requireContext(),"Siparişiniz başarıyla onaylandı.",Toast.LENGTH_SHORT).show()
        }

        viewModel.cartList.observe(viewLifecycleOwner){ cartList->
            cartAdapter.cartList=cartList
            cartAdapter.notifyDataSetChanged()

            val totalPrice = calculateTotalPrice(cartList)
            binding.textViewCartPrice.text = "Toplam Sepet Tutarı : ${totalPrice} ₺"
        }


    }

    private fun calculateTotalPrice(cartItems: List<CartItem>): Int {
        var totalPrice = 0
        for (item in cartItems) {
            totalPrice += item.yemek_fiyat * item.yemek_siparis_adet
        }
        return totalPrice
    }


}