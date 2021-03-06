package com.example.productdetail.product.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.example.productdetail.R
import com.example.productdetail.customView.ToolbarClickListener
import com.example.productdetail.product.fragment.ProductFragment
import kotlinx.android.synthetic.main.custom_toolbar.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        this.window.statusBarColor = resources.getColor(R.color.red)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductFragment.newInstance())
                .commit()
        }
    }
}