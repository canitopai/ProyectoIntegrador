package com.canitopai.proyectointegrador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.canitopai.proyectointegrador.databinding.FragmentProductListBinding
import com.canitopai.proyectointegrador.model.ProductObjectItem
import com.canitopai.proyectointegrador.model.ProductObjectRequest
import com.canitopai.proyectointegrador.network.ProductEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match

class ProductListFragment : Fragment() {


    private var _binding: FragmentProductListBinding? = null

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.56.1:3000/api/todoitems/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val binding
        get() = _binding!!
    private val adapter = ProductAdapter {

        // var myDate: String = it.dob.date.substring(0, 10)
        //var myAgeShow: Int = it.dob.age
        //var myAge: String = myAgeShow.toString()

        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
            it.name,
            it.category,
            it.description,
            it.price
        )
        findNavController().navigate(action)
        //val action = PokemonListFragmentDirections.actionPokemonListToPokemonDetailFragment(it.name,it.types.type.name[0],it.height,it.weight,it.id
        //)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
        binding.btnAdd.setOnClickListener{
            //ir al AddProduct
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        binding.rvProduct.adapter = adapter
        binding.btnAdd.setOnClickListener {
            Toast.makeText(it.context, "hjoa", Toast.LENGTH_SHORT).show()

            val service: ProductEndpoints = retrofit.create(ProductEndpoints::class.java)

            service.savePost(ProductObjectRequest(0, "name","desc", 1, "cats")).enqueue(
                object : Callback<ProductObjectItem> {


                    override fun onFailure(call: Call<ProductObjectItem>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "Algo no ha funcionado como esperábamos",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("Retrofit", "Error: ${t.localizedMessage}", t)
                    }

                    override fun onResponse(
                        call: Call<ProductObjectItem>,
                        response: Response<ProductObjectItem>
                    ) {
                        if (response.isSuccessful) {
                            adapter.submitList(listOf(response.body()))
                            Log.e("Retrofit", "Salió bien")
                        } else {
                            Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            )
        }
        requestData()
    }


    private fun requestData() {

        val service: ProductEndpoints = retrofit.create(ProductEndpoints::class.java)

        service.getProducts().enqueue(object : Callback<List<ProductObjectItem>> {


            override fun onFailure(call: Call<List<ProductObjectItem>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Algo no ha funcionado como esperábamos",
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
