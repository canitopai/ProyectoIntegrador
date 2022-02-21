package com.canitopai.proyectointegrador.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductObject(
    @SerializedName("results")
    @Expose
    val results: List<ProductObjectItem>
)