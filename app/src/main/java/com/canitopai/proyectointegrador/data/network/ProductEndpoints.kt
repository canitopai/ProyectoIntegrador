package com.canitopai.proyectointegrador.data.network
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import com.canitopai.proyectointegrador.data.model.ProductObjectRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductEndpoints {
    @GET("/api/todoitems/")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET("/api/todoitems/{id}")
    fun getProductDetailed(@Path("id")id: Int): Call<ProductObjectItem>?

    @POST("/api/todoitems/")
    fun savePost(@Body post: ProductObjectRequest): Call<ProductObjectRequest>

    @PUT("{id}")
    fun savePut(@Path("id")id: Int,@Body post: ProductObjectItem): Call<ProductObjectItem>

    @DELETE("/api/todoitems/{id}")
    fun deletePost(@Path("id")id: Int): Call<Void>

}