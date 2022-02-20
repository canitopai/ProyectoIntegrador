package com.canitopai.proyectointegrador.network

import com.canitopai.proyectointegrador.model.ProductObject
import com.canitopai.proyectointegrador.model.ProductObjectItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ProductEndpoints {
    @GET("?offset=0&limit=200")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET
    fun getProductDetailed(id: Int): Call<ProductObjectItem?>?

    @POST
    fun savePost(
        @Field("Name") title: String,
        @Field("Author") body: String,
        @Field("userId") userId: Long
    ): Call<ProductObjectItem>

}