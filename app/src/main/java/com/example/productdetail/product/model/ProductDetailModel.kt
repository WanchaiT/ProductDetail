package com.example.productdetail.product.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductDetailModel(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("content")
    var content: String? = null,
    @SerializedName("isNewProduct")
    var isNewProduct: Boolean = false,
    @SerializedName("price")
    var price: Double? = null
) : Parcelable {
}