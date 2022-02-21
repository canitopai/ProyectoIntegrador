package com.canitopai.proyectointegrador.model

import com.google.gson.annotations.SerializedName

data class ProductObjectRequest(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val category: String
)
