package com.canitopai.proyectointegrador.network

import com.canitopai.proyectointegrador.model.ProductObjectItem
import com.canitopai.proyectointegrador.model.ProductObjectRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductEndpoints {
    @GET("?offset=0&limit=200")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET
    fun getProductDetailed(id: Int): Call<ProductObjectItem?>?

    @POST("/api/todoitems/")
    fun savePost(@Body body: ProductObjectRequest): Call<ProductObjectItem>

}