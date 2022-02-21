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
import com.canitopai.proyectointegrador.databinding.FragmentProductModifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductModifyFragment : Fragment() {
    private var _binding: FragmentProductModifyBinding? = null
    private val binding
        get() = _binding!!



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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


