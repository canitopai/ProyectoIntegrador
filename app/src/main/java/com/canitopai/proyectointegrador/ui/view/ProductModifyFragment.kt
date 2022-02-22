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
import com.canitopai.proyectointegrador.data.model.ProductObjectRequest
import com.canitopai.proyectointegrador.databinding.FragmentProductModifyBinding
import kotlinx.android.synthetic.main.fragment_product_add.*
import kotlinx.android.synthetic.main.fragment_product_modify.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductModifyFragment : Fragment() {
    private var _binding: FragmentProductModifyBinding? = null
    private val binding
        get() = _binding!!
    private val args: ProductModifyFragmentArgs by navArgs()
    private var finalValue: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductModifyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener {
            getNumericValue()
            NetworkManager.service.savePut(args.myId,
                ProductObjectItem(etModCat.text.toString(),etModDesc.text.toString(),args.myId,etModName.text.toString(),finalValue)
            ).enqueue(object :
                Callback<ProductObjectItem>{
                override fun onResponse(call: Call<ProductObjectItem>, response: Response<ProductObjectItem>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Pasa por el put", Toast.LENGTH_SHORT).show()
                        Log.e("Network", "put hecho con éxito")
                    } else {
                        Log.e("Network", "error en la conexion on Response")
                    }
                }
                override fun onFailure(call: Call<ProductObjectItem>, t: Throwable) {
                    Log.e("Network", "error en la conexion",t)
                    Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                }
            })
            val action = ProductModifyFragmentDirections.actionProductModifyFragmentToProductListFragment(
            )
            findNavController().navigate(action)
        }
        binding.btnCancelar.setOnClickListener {
            //ir a list y pasar por parametro el id que va a borrar
            val action = ProductModifyFragmentDirections.actionProductModifyFragmentToProductListFragment(
            )
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun getNumericValue(){
        val value: String = etModPrice.getText().toString()
        finalValue = value.toInt()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


