package com.canitopai.proyectointegrador.data.model

data class ProductObjectRequest(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val category: String
)