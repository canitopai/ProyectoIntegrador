package com.canitopai.proyectointegrador.data.network
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import com.canitopai.proyectointegrador.data.model.ProductObjectRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ProductEndpoints {
    @GET("/api/todoitems/")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET("/api/todoitems/{id}")
    fun getProductDetailed(@Path("id")id: Int): Call<ProductObjectItem>?

    @POST("/api/todoitems/")
    fun savePost(@Body post: ProductObjectRequest): Call<ProductObjectRequest>

    @DELETE("{id}")
    fun deletePost(@Path("id")id: Int): Call<Void>

}