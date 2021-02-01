package com.example.productdetail.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.repository.OnCallbackListener
import com.example.productdetail.product.repository.ProductRepository

class ProductDetailViewModel: ViewModel() {

    var productRepository = ProductRepository()
    var productSelected: MutableLiveData<ProductDetailModel> = MutableLiveData()

    fun getProduct(id: Int) {
        productRepository.getProduct(id, object :
            OnCallbackListener<ProductDetailModel> {
            override fun onFailed(res: String) {
                print("")
            }

            override fun onFinished(response: ProductDetailModel?) {
                if (response != null) {
                    productSelected.postValue(response)
                }
            }
        })
    }
}