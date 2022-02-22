package com.canitopai.proyectointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ProductObjectRequest(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)
