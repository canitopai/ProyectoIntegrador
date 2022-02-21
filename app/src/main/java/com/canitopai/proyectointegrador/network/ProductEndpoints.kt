package com.canitopai.proyectointegrador.network
import com.canitopai.proyectointegrador.data.model.ProductObjectItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductEndpoints {
    @GET("/api/todoitems/")
    fun getProducts(): Call<List<ProductObjectItem>>

    @GET("/api/todoitems/{id}")
    fun getProductDetailed(@Path("id")id: Int): Call<ProductObjectItem>?

    @POST("posts")
    fun savePost(@Body post: ProductObjectItem): Call<ProductObjectItem>

}