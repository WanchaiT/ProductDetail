package com.example.productdetail.product.api

import com.example.productdetail.product.model.ProductDetailModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiProduct {

    @GET("products")
    fun getProduct(): Call<ArrayList<ProductDetailModel>>
}