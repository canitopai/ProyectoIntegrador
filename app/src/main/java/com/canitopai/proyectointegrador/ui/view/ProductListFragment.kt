package com.canitopai.proyectointegrador.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import com.canitopai.proyectointegrador.databinding.FragmentProductListBinding
import com.canitopai.proyectointegrador.core.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ProductAdapter {


        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
            it.name,
            it.category,
            it.description,
            it.price,
            it.id
        )
        findNavController().navigate(action)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvProduct.layoutManager = GridLayoutManager(context, 1)
        binding.rvProduct.adapter = adapter
        binding.btnAdd.setOnClickListener{
            val action = ProductListFragmentDirections.actionProductListFragmentToProductAddFragment(
            )
            findNavController().navigate(action)
        }
        requestData()
    }


    private fun requestData() {

        NetworkManager.service.getProducts().enqueue(object : Callback<List<ProductObjectItem>> {


            override fun onFailure(call: Call<List<ProductObjectItem>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esper??bamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<List<ProductObjectItem>>,
                response: Response<List<ProductObjectItem>>
            ) {
                    if (response.isSuccessful) {
                    adapter.submitList(response.body())
                    Log.e("Retrofit", "Sali?? bien")
                } else {
                    Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
