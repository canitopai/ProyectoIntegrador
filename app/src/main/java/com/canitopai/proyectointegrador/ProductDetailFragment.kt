package com.canitopai.proyectointegrador

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.canitopai.proyectointegrador.databinding.FragmentProductDetailBinding
import com.canitopai.proyectointegrador.model.ProductObjectItem
import com.canitopai.proyectointegrador.network.ProductEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!

    private var name: String = "Nombre"
    private var desc: String? = null
    private var category: String? = null
    private var price: Int = 0
    private var myId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
        binding.tvName.text = name
        binding.tvCategory.text = category
        binding.tvDesc.text = desc
        binding.tvPrice.text = price.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            name = it.getString(name).toString()
            desc = it.getString(desc).toString()
            category = it.getString(category).toString()
            price = it.getInt(price.toString())
            // type = it.getString("type")
            //height = it.getFloat("height")
            //weight = it.getFloat("weight")
            //mid = it.getInt("id")
            //sprite = it.getString("picture")
        }
    }

    private fun requestData() {
        val retrofit = Retrofit.Builder().baseUrl("http://192.168.56.1:3000/api/todoitems/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ProductEndpoints = retrofit.create(ProductEndpoints::class.java)

        service.getProductDetailed(myId)?.enqueue(object : Callback<ProductObjectItem?> {


            override fun onFailure(call: Call<ProductObjectItem?>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
            }

            override fun onResponse(
                call: Call<ProductObjectItem?>,
                response: Response<ProductObjectItem?>
            ) {
                if (response.isSuccessful) {
                    Log.e("Retrofit", "Salió bien")
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


