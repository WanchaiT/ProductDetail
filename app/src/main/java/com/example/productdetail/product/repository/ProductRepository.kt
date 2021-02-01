package com.example.productdetail.product.repository

import com.example.productdetail.product.api.ApiClient
import com.example.productdetail.product.api.ApiProduct
import com.example.productdetail.product.model.ProductDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository() {
    var apiClient = ApiClient()

    fun getAllProduct(onCallbackListener: OnCallbackListener<ArrayList<ProductDetailModel>>) {
        var apiInterface = apiClient.build().create(ApiProduct::class.java)
        var call = apiInterface.getAllProduct()

        call.enqueue(object : Callback<ArrayList<ProductDetailModel>> {
            override fun onResponse(call: Call<ArrayList<ProductDetailModel>>, response: Response<ArrayList<ProductDetailModel>>) {
                if (response.isSuccessful) onCallbackListener.onFinished(response.body())
                else {
                    onCallbackListener.onFailed()
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductDetailModel>>, t: Throwable) {
                onCallbackListener.onFailed()
            }
        })
    }

    fun getProduct(id: Int, onCallbackListener: OnCallbackListener<ProductDetailModel>) {
        var apiInterface = apiClient.build().create(ApiProduct::class.java)
        var call = apiInterface.getProduct(id)

        call.enqueue(object : Callback<ProductDetailModel> {
            override fun onResponse(call: Call<ProductDetailModel>, response: Response<ProductDetailModel>) {
                if (response.isSuccessful) onCallbackListener.onFinished(response.body())
                else {
                    onCallbackListener.onFailed(response.code().toString() + " " + response.message())
                    onCallbackListener.onFailed()
                }
            }

            override fun onFailure(call: Call<ProductDetailModel>, t: Throwable) {
                onCallbackListener.onFailed()
            }
        })
    }
}

interface OnCallbackListener<in T> {
    fun onFinished(response: T?)
    fun onFailed(){}
    fun onFailed(res : String){}
}