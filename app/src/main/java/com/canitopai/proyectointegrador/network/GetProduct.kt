package com.canitopai.proyectointegrador.network

import com.canitopai.proyectointegrador.model.ProductObject
import com.canitopai.proyectointegrador.model.ProductObjectItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetProduct {
    @GET("?offset=0&limit=200")
    fun getProducts(): Call<ProductObject>

    @GET
    fun getProductDetailed(id: Int): Call<ProductObjectItem?>?

}