package com.canitopai.proyectointegrador.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import com.canitopai.proyectointegrador.databinding.FragmentProductAddBinding
import com.canitopai.proyectointegrador.core.NetworkManager
import com.canitopai.proyectointegrador.data.model.ProductObjectRequest
import kotlinx.android.synthetic.main.fragment_product_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAddFragment : Fragment() {
    private var _binding: FragmentProductAddBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAgregar.setOnClickListener{
            postProduct()
        }
        binding.btnBack.setOnClickListener {
            val action = ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
            findNavController().navigate(action)
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }
    private fun postProduct() {
        NetworkManager.service.savePost(ProductObjectRequest(etCat.text.toString(),etDesc.text.toString(),etName.text.toString(),3)).enqueue(object :
            Callback<ProductObjectRequest> {
            override fun onResponse(call: Call<ProductObjectRequest>, response: Response<ProductObjectRequest>) {
                if (response.isSuccessful) {
                    //getMs
                    Toast.makeText(context, "Pasa por el post", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "post hecho con éxito")
                } else {
                    Log.e("Network", "error en la conexion on Response")
                }
            }
            override fun onFailure(call: Call<ProductObjectRequest>, t: Throwable) {
                Log.e("Network", "error en la conexion",t)
                Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


