package com.example.productdetail.product.api

import com.example.productdetail.product.model.ProductDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiProduct {

    @GET("products")
    fun getAllProduct(): Call<ArrayList<ProductDetailModel>>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<ProductDetailModel>
}