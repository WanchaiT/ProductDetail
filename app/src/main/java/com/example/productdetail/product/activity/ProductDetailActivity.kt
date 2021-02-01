package com.example.productdetail.product.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productdetail.R
import com.example.productdetail.product.fragment.ProductDetailFragment
import com.example.productdetail.product.fragment.ProductFragment

class ProductDetailActivity : AppCompatActivity() {

    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        intent.apply {
            id = this.getIntExtra("id", 0)
        }
        this.window.statusBarColor = resources.getColor(R.color.red)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailFragment.newInstance(id ?: 0))
                .commit()
        }
    }
}