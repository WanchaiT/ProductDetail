package com.example.productdetail.product.viewModel

import androidx.lifecycle.ViewModel
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.repository.OnCallbackListener
import com.example.productdetail.product.repository.ProductRepository

class ProductViewModel : ViewModel() {
    var productRepository = ProductRepository()


    fun getProduct() {
        productRepository.getAllProduct(object : OnCallbackListener<ArrayList<ProductDetailModel>> {
//            override fun onFinished(response: MapArrayListVenioModel<DealTableModel>?) {
//                if (response != null) {
//                    eventFinishedAddListDeal.postValue(response.data)
//                    listOfDeal.postValue(response.data as ArrayList<BaseItem>)
//                }
//
//            }

            override fun onFailed(res: String) {
                print("")
            }

            override fun onFinished(response: ArrayList<ProductDetailModel>?) {
                if (response != null) {
//                    eventFinishedAddListDeal.postValue(response.data)
//                    listOfDeal.postValue(response.data as ArrayList<BaseItem>)
                }
            }
        })
    }
}