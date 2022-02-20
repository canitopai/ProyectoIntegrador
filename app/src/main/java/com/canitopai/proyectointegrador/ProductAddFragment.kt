package com.canitopai.proyectointegrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canitopai.proyectointegrador.databinding.FragmentProductAddBinding
import com.canitopai.proyectointegrador.network.ProductEndpoints
import kotlinx.android.synthetic.main.fragment_product_add.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val retrofit = Retrofit.Builder().baseUrl("http://localhost:44319/api/todoitems/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: ProductEndpoints = retrofit.create(ProductEndpoints::class.java)
            binding.btnAgregar.setOnClickListener{
                service.savePost(etName.text,etCat.text,etDesc.text,etPrice.text)
            }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


