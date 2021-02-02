package com.example.productdetail.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetail.R
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.util.ImageUtil
import kotlinx.android.synthetic.main.item_product.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class ItemProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList = ArrayList<ProductDetailModel>()

    internal var onItemClickListener: (ProductDetailModel) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemProductViewHolder
        val item = itemList[position]
        when (holder) {
            is ItemProductAdapter.ItemProductViewHolder -> {
                holder.bind(item, onItemClickListener)
            }
        }
    }

    class ItemProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductDetailModel, onItemClickListener: (ProductDetailModel) -> Unit) {
            setEventListener(item, onItemClickListener)
            setView(item)
        }

        private fun setEventListener(item: ProductDetailModel,
                                     onItemClickListener: (ProductDetailModel) -> Unit) {
            itemView.lin_container.setOnClickListener {
                onItemClickListener(item)
            }
        }

        private fun setView(item: ProductDetailModel) {

            itemView.tv_product_name.text = item.title ?: ""

            if (!item.image.isNullOrEmpty()) {
                ImageUtil.loadImage(
                        context = itemView.imv_product.context,
                        imageView = itemView.imv_product,
                        path = item.image ?: ""
                )
            }
            itemView.tv_new.visibility = if(item.isNewProduct) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
            val df = DecimalFormat("#.##")
            itemView.tv_product_price.text = df.format(item.price ?: 0.0)
        }
    }
}

interface OnItemRecyclerClick {
    fun onItemRecyclerClick(v: View?, pos: Int?)
}