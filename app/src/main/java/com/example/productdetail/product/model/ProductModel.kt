package com.example.productdetail.product.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductModel (
    @SerializedName("f")
    var productList: ArrayList<ProductDetailModel>? = null
) : Parcelable {
}