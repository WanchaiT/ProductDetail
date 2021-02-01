package com.example.productdetail.product.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productdetail.R
import com.example.productdetail.product.fragment.ProductFragment

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductFragment.newInstance())
                .commitNow()
        }
    }
}