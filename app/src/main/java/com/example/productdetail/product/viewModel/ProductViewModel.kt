package com.example.productdetail.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.repository.OnCallbackListener
import com.example.productdetail.product.repository.ProductRepository

class ProductViewModel : ViewModel() {

    var productRepository = ProductRepository()
    var product: MutableLiveData<ArrayList<ProductDetailModel>> = MutableLiveData()

    fun getAllProduct() {
        productRepository.getAllProduct(object : OnCallbackListener<ArrayList<ProductDetailModel>> {
            override fun onFailed(res: String) {
                print("")
            }

            override fun onFinished(response: ArrayList<ProductDetailModel>?) {
                if (response != null) {
                    product.postValue(response)
                }
            }
        })
    }
}