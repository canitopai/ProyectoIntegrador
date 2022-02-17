package com.canitopai.proyectointegrador.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetProduct {
    @GET("?offset=0&limit=200")
    fun getPkmns(): Call<ProductInfo>

    @GET
    fun getPkmnDetailed(@Url url: String?): Call<ProductObject?>?

}