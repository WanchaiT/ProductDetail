package com.example.productdetail.product.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetail.R
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.repository.OnCallbackListener
import com.example.productdetail.product.repository.ProductRepository

class ProductDetailViewModel: ViewModel() {

    lateinit var context: Context
    var productRepository = ProductRepository()
    var productSelected: MutableLiveData<ProductDetailModel> = MutableLiveData()
    var onFailed: MutableLiveData<String> = MutableLiveData()

    fun getProduct(id: Int) {
        productRepository.getProduct(id, object : OnCallbackListener<ProductDetailModel> {
            override fun onFailed() {
                onFailed.postValue(context.getString(R.string.text_someting_went_wong))
            }

            override fun onFailed(res: String) {
                onFailed.postValue(res.toString())
            }

            override fun onFinished(response: ProductDetailModel?) {
                if (response != null) {
                    productSelected.postValue(response)
                }
            }
        })
    }
}