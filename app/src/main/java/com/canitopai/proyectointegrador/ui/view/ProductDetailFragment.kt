package com.canitopai.proyectointegrador.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import com.canitopai.proyectointegrador.databinding.FragmentProductDetailBinding
import com.canitopai.proyectointegrador.core.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()

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
        arguments?.let {

            name = it.getString(name).toString()
            desc = it.getString(desc).toString()
            category = it.getString(category).toString()
            price = it.getInt(price.toString())

        }
        binding.tvName.text = args.name
        binding.tvCategory.text = args.desc
        binding.tvDesc.text = args.category
        binding.tvPrice.text = args.price.toString()

        binding.btnDelete.setOnClickListener {
            //ir a list y pasar por parametro el id que va a borrar
            NetworkManager.service.deletePost(args.myId).enqueue(object :
                Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Pasa por el put", Toast.LENGTH_SHORT).show()
                        Log.e("Network", "put hecho con éxito")
                    } else {
                        Log.e("Network", "error en la conexion on Response")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("Network", "error en la conexion",t)
                    Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.btnModify.setOnClickListener {
            //ir a modify
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductModifyFragment(args.myId
            )
            findNavController().navigate(action)
        }
        binding.btnBack.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment()
            findNavController().navigate(action)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun requestData() {


        NetworkManager.service.getProductDetailed(args.myId)?.enqueue(object : Callback<ProductObjectItem?> {


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


