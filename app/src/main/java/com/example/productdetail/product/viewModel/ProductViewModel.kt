package com.example.productdetail.product.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetail.R
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.repository.OnCallbackListener
import com.example.productdetail.product.repository.ProductRepository

class ProductViewModel : ViewModel() {

    lateinit var context: Context
    var productRepository = ProductRepository()
    var product: MutableLiveData<ArrayList<ProductDetailModel>> = MutableLiveData()
    var onFailed: MutableLiveData<String> = MutableLiveData()

    fun getAllProduct() {
        productRepository.getAllProduct(object : OnCallbackListener<ArrayList<ProductDetailModel>> {
            override fun onFailed() {
                onFailed.postValue(context.getString(R.string.text_someting_went_wong))
            }

            override fun onFailed(res: String) {
                onFailed.postValue(toString())
            }

            override fun onFinished(response: ArrayList<ProductDetailModel>?) {
                if (response != null) {
                    product.postValue(response)
                }
            }
        })
    }
}